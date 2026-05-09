package com.jean.auth_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/*
 * Entity representa uma tabela no banco de dados.
 */
@Entity
public class User {

    /*
     * ID único do usuário
     */
    @Id

    /*
     * Geração automática do ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nome do usuário
     */
    private String name;

    /*
     * Email único no sistema
     */
    @Column(unique = true)
    private String email;

    /*
     * Senha do usuário
     */
    private String password;
}