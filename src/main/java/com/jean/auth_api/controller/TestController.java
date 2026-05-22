package com.jean.auth_api.controller;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /*
     * Rota protegida
     * Só funciona se enviar token JWT válido
     */
    @GetMapping("/test")
    public String test(Authentication authentication) {

        return "Usuário autenticado: " + authentication.getName();
    }
}