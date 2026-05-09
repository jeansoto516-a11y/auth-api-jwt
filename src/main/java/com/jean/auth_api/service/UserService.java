package com.jean.auth_api.service;

import com.jean.auth_api.model.User;
import com.jean.auth_api.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service é onde ficam as regras de negócio da aplicação.
 * Aqui NÃO deve ter lógica de banco direto nem lógica de controller.
 */
@Service
public class UserService {

    // Injeta o repository para acessar o banco de dados
    private final UserRepository userRepository;

    /*
     * Construtor com injeção de dependência
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * Método para criar um novo usuário
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*
     * Retorna todos os usuários do banco
     */
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    /*
     * Busca usuário pelo email
     * Se não encontrar, lança erro
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}