package com.jean.auth_api.controller;

import com.jean.auth_api.dto.AuthRequest;
import com.jean.auth_api.dto.AuthResponse;
import com.jean.auth_api.model.User;
import com.jean.auth_api.repository.UserRepository;
import com.jean.auth_api.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Repository responsável por acessar a tabela de usuários
    @Autowired
    private UserRepository userRepository;

    // Serviço responsável por gerar o token JWT
    @Autowired
    private JwtService jwtService;

    // Encoder usado para criptografar e validar senhas
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint para registrar um novo usuário
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salva usuário no banco
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Busca usuário pelo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
       
        boolean senhaCorreta = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        // Se a senha estiver incorreta
        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}