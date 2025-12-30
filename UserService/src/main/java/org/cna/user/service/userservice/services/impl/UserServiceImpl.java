package org.cna.user.service.userservice.services.impl;

import org.cna.user.service.userservice.entities.User;
import org.cna.user.service.userservice.exceptions.ResourceNotFoundException;
import org.cna.user.service.userservice.repositories.UserRepository;
import org.cna.user.service.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        return userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("user with give id : "+userID +" not found"));
    }
}
