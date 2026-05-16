package com.jean.auth_api.controller;

import com.jean.auth_api.dto.AuthRequest;
import com.jean.auth_api.dto.AuthResponse;
import com.jean.auth_api.model.User;
import com.jean.auth_api.repository.UserRepository;
import com.jean.auth_api.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}