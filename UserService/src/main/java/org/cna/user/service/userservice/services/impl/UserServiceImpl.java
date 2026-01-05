package org.cna.user.service.userservice.services.impl;

import org.cna.user.service.userservice.entities.Rating;
import org.cna.user.service.userservice.entities.User;
import org.cna.user.service.userservice.exceptions.ResourceNotFoundException;
import org.cna.user.service.userservice.externalservices.HotelService;
import org.cna.user.service.userservice.externalservices.RatingService;
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

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

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

        // Fetch ratings by userId by calling RATING-SERVICE in old fashioned way with help of REST-TEMPLATE

        //getRatingsByUserIdRestTemplateCall(userID);

        //feign client way of calling
        List<Rating> ratings=ratingService.getRatingsByUserId(user.getUserID());

        // Fetch hotel details for each rating by restTemplate  way
 /*       if (ratingsByUserId != null) {
            for (Rating r : ratingsByUserId) {
                Hotel hotel = restTemplate.getForEntity(
                        "http://HOTEL-SERVICE/hotels/" + r.getHotelId(),
                        Hotel.class
                ).getBody();
                r.setHotel(hotel);
            }
        }*/

        if (ratings != null) {
            for (Rating r : ratings) {
                Hotel hotel = hotelService.getHotelById(r.getHotelId());
                r.setHotel(hotel);
            }
        }

       user.setRatings(ratings);

        return user;
    }

    private void getRatingsByUserIdRestTemplateCall(String userID) {
        ResponseEntity<List<Rating>> ratingResponse = restTemplate.exchange(
                 "http://RATING-SERVICE/ratings/users/" + userID,
                 HttpMethod.GET,
                 null,
                 new ParameterizedTypeReference<List<Rating>>() {}
         );
        List<Rating> ratingsByUserId = ratingResponse.getBody();
    }
}
