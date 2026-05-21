package com.jean.auth_api.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Desabilita CSRF
            .csrf(csrf -> csrf.disable())

            // Desabilita sessão
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Permite H2
            .headers(headers ->
                    headers.frameOptions(frame -> frame.disable())
            )

            // Configura permissões
            .authorizeHttpRequests(auth -> auth

                // Rotas públicas
                .requestMatchers(
                        "/auth/**",
                        "/h2-console/**"
                ).permitAll()

                // Qualquer outra rota precisa de token
                .anyRequest().authenticated()
            )

            // Adiciona filtro JWT antes do filtro padrão
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}