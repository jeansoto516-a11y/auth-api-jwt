package com.jean.auth_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Desabilita CSRF
            .csrf(csrf -> csrf.disable())

            // Permite H2 Console
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )

            // Configuração de permissões
            .authorizeHttpRequests(auth -> auth

                // Libera rotas públicas
                .requestMatchers(
                    "/auth/**",
                    "/h2-console/**",
                    "/test"
                ).permitAll()

                // Qualquer outra rota precisa de autenticação
                .anyRequest().authenticated()
            );

        return http.build();
    }
}