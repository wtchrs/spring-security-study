package com.example.ssia.main.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.signing.key}")
    private String signingKey;

    public String createToken(Map<String, ?> claims) {
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getClaim(String token, String claimName) {
        return getClaim(token, claimName, String.class);
    }

    public <T> T getClaim(String token, String claimName, Class<T> requiredType) {
        Claims claims = getClaims(token);
        return claims.get(claimName, requiredType);
    }
}
