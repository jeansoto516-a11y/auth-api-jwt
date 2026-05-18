package com.jean.auth_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "minha_chave_super_secreta_jwt_123456_123456789";

    /*
     * Gera chave segura para assinatura do JWT
     */
    private SecretKey getSignKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    /*
     * Gera token JWT
     */
    public String generateToken(String email) {

        return Jwts.builder()

                // usuário dono do token
                .subject(email)

                // data de criação
                .issuedAt(new Date())

                // expira em 1 hora
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))

                // assinatura
                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                .compact();
    }

    /*
     * Extrai email do token
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    /*
     * Extrai qualquer informação do token
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /*
     * Extrai todos os dados do token
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSignKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    /*
     * Valida token
     */
    public boolean isTokenValid(String token, String email) {

        final String username = extractUsername(token);

        return username.equals(email);
    }
}