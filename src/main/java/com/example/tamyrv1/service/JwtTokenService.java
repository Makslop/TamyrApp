package com.example.tamyrv1.service;

import com.example.tamyrv1.model.JwtToken;
import com.example.tamyrv1.repository.JwtTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;

    public JwtTokenService(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public void saveToken(String token, Long userId, LocalDateTime expiration) {
        jwtTokenRepository.deleteByUserId(userId); // Удаляем старые токены
        JwtToken jwtToken = new JwtToken(token, userId, expiration);
        jwtTokenRepository.save(jwtToken);
    }

    public boolean isTokenValid(String token) {
        return jwtTokenRepository.findByToken(token)
                .map(jwtToken -> jwtToken.getExpiration().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    public void deleteToken(String token) {
        jwtTokenRepository.deleteByToken(token);
    }
}
