# Microservices Architecture - CNA Training

A comprehensive microservices-based application demonstrating Cloud Native Architecture principles using Spring Boot 4.0.1 and Java 21.

## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Microservices](#microservices)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Configuration](#database-configuration)

---

## 🎯 Overview

This project demonstrates a microservices architecture with three independent services that work together to manage users, hotels, and ratings. Each service is built with Spring Boot and uses different database technologies to showcase polyglot persistence.

## 🏗️ Architecture

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│  UserService    │     │  HotelService   │     │  RatingService  │
│   (Port 8081)   │     │   (Port 8082)   │     │   (Port 8083)   │
│                 │     │                 │     │                 │
│   MySQL DB      │     │  PostgreSQL DB  │     │   MongoDB       │
└─────────────────┘     └─────────────────┘     └─────────────────┘
```

---

## 🚀 Microservices

### 1. UserService (Port: 8081)

**Purpose:** Manages user information and their associated ratings.

**Database:** MySQL (Port: 3306)

**Key Features:**
- Create, read, update, and delete user records
- Store user details (name, email, about, rating)
- Maintain transient relationships with ratings

**Entity Structure:**
```java
User {
    String userID (Primary Key)
    String userName
    String email
    String about
    int rating
    List<Rating> ratings (Transient)
}
```

**Technology:**
- Spring Boot 4.0.1
- Spring Data JPA
- MySQL Connector
- Lombok

---

### 2. HotelService (Port: 8082)

**Purpose:** Manages hotel information and inventory.

**Database:** PostgreSQL (Port: 5433)

**Key Features:**
- CRUD operations for hotel records
- Store hotel details (name, location, about)
- Global exception handling with custom exceptions
- RESTful API endpoints

**Entity Structure:**
```java
Hotel {
    String hotelId (Primary Key)
    String hotelName
    String location
    String about
}
```

**Technology:**
- Spring Boot 4.0.1
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- Custom Exception Handling (ResourceNotFoundException)

---

### 3. RatingService (Port: 8083)

**Purpose:** Manages ratings given by users to hotels.

**Database:** MongoDB (Port: 27017)

**Key Features:**
- Create and retrieve ratings
- Query ratings by user ID
- Query ratings by hotel ID
- NoSQL document storage for flexible rating data

**Entity Structure:**
```java
Rating {
    String ratingId (Primary Key)
    String userId
    String hotelId
    int rating
    String feedback
}
```

**Technology:**
- Spring Boot 4.0.1
- Spring Data MongoDB
- MongoDB Driver
- Lombok

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Spring Boot | 4.0.1 | Framework |
| Maven | 3.x | Build Tool |
| MySQL | 8.x | UserService Database |
| PostgreSQL | 15.x | HotelService Database |
| MongoDB | 7.x | RatingService Database |
| Lombok | Latest | Reduce Boilerplate Code |

---

## 📦 Prerequisites

Before running the microservices, ensure you have the following installed:

1. **Java Development Kit (JDK) 21**
   ```bash
   java -version
   ```

2. **Maven 3.x**
   ```bash
   mvn -version
   ```

3. **MySQL Server** (Port: 3306)
   - Database: `microservices`
   - Username: `root`
   - Password: `root`

4. **PostgreSQL Server** (Port: 5433)
   - Database: `microservices`
   - Username: `postgres`
   - Password: `root`

5. **MongoDB Server** (Port: 27017)
   - Database: `microservices`

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/amadeus-res/cna-microservices.git
cd Microservices
```

### 2. Setup Databases

**MySQL:**
```sql
CREATE DATABASE microservices;
```

**PostgreSQL:**
```sql
CREATE DATABASE microservices;
```

**MongoDB:**
```bash
# MongoDB will auto-create the database on first connection
mongosh
use microservices
```

### 3. Build All Services
```bash
# Build UserService
cd UserService
mvn clean install
cd ..

# Build HotelService
cd HotelService
mvn clean install
cd ..

# Build RatingService
cd RatingService
mvn clean install
cd ..
```

### 4. Run the Services

**Terminal 1 - UserService:**
```bash
cd UserService
mvn spring-boot:run
```

**Terminal 2 - HotelService:**
```bash
cd HotelService
mvn spring-boot:run
```

**Terminal 3 - RatingService:**
```bash
cd RatingService
mvn spring-boot:run
```

---

## 📡 API Endpoints

### UserService (http://localhost:8081)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Create a new user |
| GET | `/users` | Get all users |
| GET | `/users/{userId}` | Get user by ID |
| PUT | `/users/{userId}` | Update user |
| DELETE | `/users/{userId}` | Delete user |

**Sample Request (Create User):**
```json
POST http://localhost:8081/users
{
    "userName": "John Doe",
    "email": "john.doe@example.com",
    "about": "Software Developer",
    "rating": 5
}
```

---

### HotelService (http://localhost:8082)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/hotels` | Create a new hotel |
| GET | `/hotels` | Get all hotels |
| GET | `/hotels/{hotelId}` | Get hotel by ID |
| PUT | `/hotels/{hotelId}` | Update hotel |
| DELETE | `/hotels/{hotelId}` | Delete hotel |

**Sample Request (Create Hotel):**
```json
POST http://localhost:8082/hotels
{
    "hotelName": "Grand Plaza",
    "location": "New York",
    "about": "Luxury hotel in downtown Manhattan"
}
```

---

### RatingService (http://localhost:8083)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/ratings` | Create a new rating |
| GET | `/ratings` | Get all ratings |
| GET | `/ratings/{ratingId}` | Get rating by ID |
| GET | `/ratings/users/{userId}` | Get ratings by user |
| GET | `/ratings/hotels/{hotelId}` | Get ratings by hotel |

**Sample Request (Create Rating):**
```json
POST http://localhost:8083/ratings
{
    "userId": "user-uuid-here",
    "hotelId": "hotel-uuid-here",
    "rating": 5,
    "feedback": "Excellent service and clean rooms!"
}
```

---

## 🗄️ Database Configuration

### UserService - MySQL Configuration
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/microservices
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### HotelService - PostgreSQL Configuration
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/microservices
    username: postgres
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### RatingService - MongoDB Configuration
```yaml
spring:
  mongodb:
    database: microservices
    uri: mongodb://localhost:27017/
```

---

## 🔧 Project Structure

```
Microservices/
├── UserService/
│   ├── src/main/java/org/cna/user/service/userservice/
│   │   ├── entities/
│   │   │   └── User.java
│   │   ├── repositories/
│   │   │   └── UserRepository.java
│   │   ├── services/
│   │   │   ├── UserService.java
│   │   │   └── impl/UserServiceImpl.java
│   │   └── controllers/
│   │       └── UserController.java
│   └── pom.xml
│
├── HotelService/
│   ├── src/main/java/org/cna/hotel/service/
│   │   ├── entities/
│   │   │   ├── Hotel.java
│   │   │   └── HotelRepository.java
│   │   ├── services/
│   │   │   ├── HotelService.java
│   │   │   ├── impl/HotelServiceImpl.java
│   │   │   └── exceptions/
│   │   │       ├── ResourceNotFoundException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── controllers/
│   │       └── HotelController.java
│   └── pom.xml
│
└── RatingService/
    ├── src/main/java/org/cna/rating/service/RatingService/
    │   ├── entities/
    │   │   └── Rating.java
    │   ├── repositories/
    │   │   └── RatingRepository.java
    │   ├── services/
    │   │   ├── RatingService.java
    │   │   └── RatingServiceImpl.java
    │   └── controllers/
    │       └── RatingController.java
    └── pom.xml
```

---

## 🧪 Testing

Each service includes test classes:

```bash
# Run tests for UserService
cd UserService
mvn test

# Run tests for HotelService
cd HotelService
mvn test

# Run tests for RatingService
cd RatingService
mvn test
```

---

## 🐛 Troubleshooting

### Common Issues:

1. **Port Already in Use:**
   - Check if another application is using ports 8081, 8082, or 8083
   - Change the port in `application.yml` if needed

2. **Database Connection Failed:**
   - Verify database servers are running
   - Check credentials in `application.yml`
   - Ensure databases are created

3. **Build Failures:**
   - Ensure Java 21 is installed
   - Run `mvn clean install` to rebuild
   - Check for dependency conflicts

---

## 📝 Future Enhancements

- [ ] Add API Gateway (Spring Cloud Gateway)
- [ ] Implement Service Discovery (Eureka)
- [ ] Add Circuit Breaker (Resilience4j)
- [ ] Implement Distributed Tracing (Zipkin/Sleuth)
- [ ] Add Centralized Configuration (Spring Cloud Config)
- [ ] Implement Authentication & Authorization (OAuth2/JWT)
- [ ] Add Docker containerization
- [ ] Implement Kubernetes deployment
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Implement event-driven communication (Kafka/RabbitMQ)

---

## 👥 Contributors

- CNA Training Team

---

## 📄 License

This project is created for educational purposes as part of CNA (Cloud Native Architecture) training.

---

## 📞 Support

For issues and questions, please create an issue in the GitHub repository.

---

**Happy Coding! 🚀**
