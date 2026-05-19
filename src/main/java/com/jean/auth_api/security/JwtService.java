package com.jean.auth_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    /*
     * Chave secreta JWT
     *
     * IMPORTANTE:
     * Para HS256 a chave precisa ter pelo menos 32 caracteres.
     */
    private static final String SECRET_KEY =
            "minha_chave_super_secreta_jwt_123456789";

    /*
     * Gera chave segura para assinatura do token
     */
    private SecretKey getSignKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    /*
     * Gerar token JWT
     */
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey())
                .compact();
    }

    /*
     * Extrair email do token
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    /*
     * Extrair informações do token
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /*
     * Extrair todas as claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /*
     * Validar token
     */
    public boolean isTokenValid(String token, String email) {

        final String username = extractUsername(token);

        return username.equals(email);
    }
}