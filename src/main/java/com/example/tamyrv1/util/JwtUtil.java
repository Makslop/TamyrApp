package com.example.tamyrv1.util;

import com.example.tamyrv1.service.JwtTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.time.LocalDateTime;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_TIME = 3600000; // 1 час в миллисекундах
    private final JwtTokenService jwtTokenService;

    public JwtUtil(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    public String generateToken(String email, Long userId) {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
        jwtTokenService.saveToken(token, userId, LocalDateTime.now().plusHours(1));
        return token;
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        return jwtTokenService.isTokenValid(token) && extractEmail(token) != null;
    }
}
