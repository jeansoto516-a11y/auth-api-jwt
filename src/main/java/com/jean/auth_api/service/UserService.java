package com.jean.auth_api.service;

import com.jean.auth_api.model.User;
import com.jean.auth_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Criar usuário
    public User createUser(User user) {

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    //Listar usuários
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    //Buscar usuário por ID
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    //Deletar usuário
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}