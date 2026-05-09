package com.jean.auth_api.repository;

import com.jean.auth_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * Repository é a camada responsável por conversar diretamente com o banco de dados.
 * Aqui a gente não escreve SQL manualmente.
 * O Spring Data JPA gera as consultas automaticamente.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * Método customizado para buscar usuário pelo email.
     * Retorna Optional porque o usuário pode ou não existir.
     */
    Optional<User> findByEmail(String email);
}