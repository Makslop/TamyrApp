package com.example.tamyrv1.handler;

import com.example.tamyrv1.model.RefreshToken;
import com.example.tamyrv1.repository.AccessTokenRepository;
import com.example.tamyrv1.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    public CustomLogoutHandler(AccessTokenRepository accessTokenRepository, RefreshTokenRepository refreshTokenRepository){
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication){
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            return;
        }

        String token = authHeader.substring(7);

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElse(null);
        Long userId = refreshToken.getUser().getId();
        if(refreshToken != null){
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            accessTokenRepository.revokeAllTokens(userId);
        }
    }
}
