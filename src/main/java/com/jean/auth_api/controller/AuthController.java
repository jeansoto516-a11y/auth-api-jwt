package com.jean.auth_api.controller;

import com.jean.auth_api.dto.RegisterRequestDTO;
import com.jean.auth_api.model.User;
import com.jean.auth_api.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller responsável pelas rotas de autenticação.
//Aqui ficam os endpoints da API.
@RestController


//Define o prefixo das rotas:
@RequestMapping("/auth")
public class AuthController {

    // Injeta o UserService
    private final UserService userService;

    // Construtor com injeção de dependência
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //Endpoint de cadastro de usuário
    //POST /auth/register

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequestDTO data) {

        // Cria novo usuário
        User user = new User();

        // Preenche os dados
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());

        // Salva usuário no banco
        userService.createUser(user);

        // Retorna resposta de sucesso
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}