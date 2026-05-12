package com.jean.auth_api.dto;

// DTO usado para receber os dados
// do cadastro de usuário.
//DTO = Data Transfer Object
public class RegisterRequestDTO {

    // Nome do usuário
    private String name;

    // Email do usuário
    private String email;

    // Senha do usuário
    private String password;

    // nome, email e senha
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    //Setter do email
    public void setEmail(String email) {
        this.email = email;
    }

    //Getter da senha
    public String getPassword() {
        return password;
    }

    //Setter da senha
    public void setPassword(String password) {
        this.password = password;
    }
}