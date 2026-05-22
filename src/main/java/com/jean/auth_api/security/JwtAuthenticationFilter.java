package com.jean.auth_api.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Pega rota atual
        String path = request.getServletPath();

        /*
         * Ignora rotas públicas
         */
        if (
                path.equals("/auth/login") ||
                path.equals("/auth/register") ||
                path.startsWith("/h2-console")
        ) {

            filterChain.doFilter(request, response);
            return;
        }

        // Pega header Authorization
        final String authHeader = request.getHeader("Authorization");

        // Se não tiver token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Remove Bearer
        String token = authHeader.substring(7);

        // Extrai email
        String email = jwtService.extractUsername(token);

        // Se usuário ainda não autenticado
        if (
                email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null
        ) {

            UserDetails userDetails = User
                    .withUsername(email)
                    .password("")
                    .authorities("USER")
                    .build();

            // Valida token
            if (jwtService.isTokenValid(token, email)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}