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

            // API sem sessão
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Libera H2 Console
            .headers(headers ->
                    headers.frameOptions(frame -> frame.disable())
            )

            // Configuração de rotas
            .authorizeHttpRequests(auth -> auth

                    // ROTAS PÚBLICAS
                    .requestMatchers(
                            "/auth/register",
                            "/auth/login",
                            "/h2-console/**"
                    ).permitAll()

                    // Qualquer outra rota exige autenticação
                    .anyRequest().authenticated()
            )

            // Adiciona filtro JWT
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}