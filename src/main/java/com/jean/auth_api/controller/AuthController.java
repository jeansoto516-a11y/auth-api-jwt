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

    /*
     * Repository responsável pelas operações no banco
     */
    @Autowired
    private UserRepository userRepository;

    /*
     * Serviço responsável por gerar e validar JWT
     */
    @Autowired
    private JwtService jwtService;

    /*
     * Responsável por criptografar e validar senhas
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Endpoint de cadastro de usuário
     */
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        // Criptografa a senha antes de salvar
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Salva usuário no banco
        return userRepository.save(user);
    }

    /*
     * Endpoint de login
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Busca usuário pelo email
        User user = userRepository.findByEmail(
                request.getEmail()
        );

        // Verifica se usuário existe
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        // Valida senha enviada com senha criptografada do banco
        boolean senhaCorreta = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        // Se senha estiver incorreta
        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        // Gera token JWT
        String token = jwtService.generateToken(
                user.getEmail()
        );

        // Retorna token
        return new AuthResponse(token);
    }
}