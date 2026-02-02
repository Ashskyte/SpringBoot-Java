package org.cna.user.service.userservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.cna.user.service.userservice.entities.User;
import org.cna.user.service.userservice.exceptions.ResourceNotFoundException;
import org.cna.user.service.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    //fetch user by id
    @GetMapping({"/{userId}"})
    @CircuitBreaker(name = "ratingAndHotelServiceCB", fallbackMethod = "ratingAndHotelServiceFallback")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    public ResponseEntity<User> ratingAndHotelServiceFallback(String userId, Exception e){
        if(e instanceof ResourceNotFoundException){
            return ResponseEntity.ok(User.builder().userID(userId).userName("Default User").about("Default User because one of the services are down").rating(0).build());
        }
        return ResponseEntity.ok(User.builder().userID(userId).userName("Default User").about("Default User because one of the services are down").rating(0).build());
    }


    //fetch all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
