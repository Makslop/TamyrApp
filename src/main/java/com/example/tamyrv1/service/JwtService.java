package com.example.tamyrv1.service;

import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.AccessTokenRepository;
import com.example.tamyrv1.repository.RefreshTokenRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;
import javax.crypto.SecretKey;

@Service
public class JwtService {
    @Value("${security.jwt.secret_key}")
    private String secretKey;
    @Value("${security.jwt.access_token_expiration}")
    private long accessTokenExpiration;
    @Value("${security.jwt.refresh_token_expiration}")
    private long refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;
    public JwtService(RefreshTokenRepository refreshTokenRepository, AccessTokenRepository accessTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;this.accessTokenRepository = accessTokenRepository;}

    private SecretKey getSecretKey() {byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private String generateToken(User user, Long expiration) {
        // Генерация токена с использованием параметров пользователя и времени истечения
        return Jwts.builder()
                .setSubject(user.getUsername()) // Устанавливаем subject как имя пользователя
                .setIssuedAt(new Date(System.currentTimeMillis())) // Устанавливаем дату выпуска токена
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Устанавливаем время истечения токена
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Указываем ключ подписи и алгоритм
                .compact(); // Сериализуем JWT в строку
    }

    private Key getSigningKey() {
        // Возвращаем секретный ключ для подписи токена
        byte[] keyBytes = "4eeab38d706831be4b36612ead768ef8182d1dd6f0e14e5dc934652e297fb16a".getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
