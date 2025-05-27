package com.example.tamyrv1.service;

import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.AccessTokenRepository;
import com.example.tamyrv1.repository.RefreshTokenRepository;
import com.google.common.base.Function;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;
import javax.crypto.SecretKey;

@Service
@Transactional
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

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(User user){
        return generateToken(user, accessTokenExpiration);
    }

    public String generateRefreshToken(User user){
        return generateToken(user, refreshTokenExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isAccessTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    public boolean isValidAccess(String token, UserDetails user) {

        String username = extractUsername(token);

        boolean isValidToken = accessTokenRepository.findByToken(token)
                .map(t -> !t.isLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && isAccessTokenExpired(token)
                && isValidToken;
    }

    public boolean isValidRefresh(String token, User user){
        String username = extractUsername(token);

        boolean isValidRefreshToken = refreshTokenRepository.findByToken(token)
                .map(t -> !t.isRevoked()).orElse(false);

        return username.equals(user.getUsername())
                && isAccessTokenExpired(token)
                && isValidRefreshToken;
    }

    private Key getSigningKey() {
        // Возвращаем секретный ключ для подписи токена
        byte[] keyBytes = "4eeab38d706831be4b36612ead768ef8182d1dd6f0e14e5dc934652e297fb16a".getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
