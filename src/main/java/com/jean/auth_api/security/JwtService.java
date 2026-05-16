package com.jean.auth_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Chave secreta
    private static final String SECRET_KEY = "minha_chave_super_secreta_jwt_123456";

    // Gerar chave
    private Key getSignKey() {

        return new SecretKeySpec(
                SECRET_KEY.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    // Gerar token
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey())
                .compact();
    }

    // Extrair email do token
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // Extrair claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // Extrair tudo do token
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Validar token
    public boolean isTokenValid(String token, String email) {

        final String username = extractUsername(token);

        return username.equals(email);
    }
}