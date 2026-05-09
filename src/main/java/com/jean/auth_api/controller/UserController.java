package com.jean.auth_api.controller;

import com.jean.auth_api.model.User;
import com.jean.auth_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller é a camada que expõe a API.
//Aqui ficam os endpoints que o frontend ou Postman vão acessar.

@RestController
@RequestMapping("/users") //tudo aqui começa com /users
public class UserController {

    //injeta o serviçce (onde está a regra de negocio)
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        //chama a camada de serviço para salvar o usuario
        User createdUser = userService.createUser(user);

        //retorna resposta HTTP 201 
        return ResponseEntity.status(201).body(createdUser);
    }

    //Endpoint para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    //Endpoint para buscar usuário por email
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {

        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}