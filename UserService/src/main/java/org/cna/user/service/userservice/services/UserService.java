package org.cna.user.service.userservice.services;

import org.cna.user.service.userservice.entities.User;

import java.util.List;

public interface UserService {

    // operations for user

    //add user
    User save(User user);

    //get all Users
    List<User> getAllUsers();

    //get single user by id
    User getUserById(String userID);

    //todo : complete below also
    //delete
    //update

}
