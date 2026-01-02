package org.cna.user.service.userservice.services.impl;

import org.cna.user.service.userservice.entities.Rating;
import org.cna.user.service.userservice.entities.User;
import org.cna.user.service.userservice.exceptions.ResourceNotFoundException;
import org.cna.user.service.userservice.repositories.UserRepository;
import org.cna.user.service.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.cna.user.service.userservice.entities.Hotel;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User save(User user) {
        String randomUserId=UUID.randomUUID().toString();
        User newUser=User.builder().email(user.getEmail()).userID(randomUserId).about(user.getAbout()).userName(user.getUserName()).rating(user.getRating()).build();
      //  user.setUserID(randomUserId);
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userID) {

        User user= userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("user with give id : "+userID +" not found"));
        //fetch ratings by userId by calling RATING-SERVICE
      //  http://localhost:8083/ratings/users/48b38eef-30f7-4c15-913a-6634ec1c8a71

        // Fetch ratings by userId by calling RATING-SERVICE
        ResponseEntity<List<Rating>> ratingResponse = restTemplate.exchange(
                "http://localhost:8083/ratings/users/" + userID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {}
        );
        List<Rating> ratingsByUserId = ratingResponse.getBody();

        // Fetch hotel details for each rating
        if (ratingsByUserId != null) {
            for (Rating r : ratingsByUserId) {
                Hotel hotel = restTemplate.getForEntity(
                        "http://localhost:8082/hotels/" + r.getHotelId(),
                        Hotel.class
                ).getBody();
                r.setHotel(hotel);
            }
        }

       user.setRatings(ratingsByUserId);

        return user;
    }
}
