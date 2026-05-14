package com.jean.auth_api.controller;

import com.jean.auth_api.model.User;
import com.jean.auth_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Criar usuário
    @PostMapping
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    //Listar usuários
    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    //Buscar usuário por ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    //Deletar usuário
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }
}