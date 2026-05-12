package com.jean.auth_api.controller;

import com.jean.auth_api.model.User;
import com.jean.auth_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);
    }
}