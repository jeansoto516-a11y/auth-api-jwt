package com.jean.auth_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// Entity representa uma tabela no banco de dados.

@Entity(name = "users")
public class User {

    @Id

    //Geração automática do ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário
    private String name;

    // Email único no sistema
    @Column(unique = true)
    private String email;

    // Senha do usuário
    
    private String password;

    // Retorna o ID
    public Long getId() {
        return id;
    }

    // Define o ID
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o nome
    public String getName() {
        return name;
    }

    // Define o nome
    public void setName(String name) {
        this.name = name;
    }

    // Retorna o email
    public String getEmail() {
        return email;
    }

    // Define o email
    public void setEmail(String email) {
        this.email = email;
    }

    // Retorna a senha
    public String getPassword() {
        return password;
    }

    // Define a senha
    public void setPassword(String password) {
        this.password = password;
    }
}