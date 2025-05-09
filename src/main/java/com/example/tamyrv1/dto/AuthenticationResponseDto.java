package com.example.tamyrv1.dto;

public class AuthenticationResponseDto {
    private final String accessToken;
    private final String refreshToken;
    private final Long userId;

    public AuthenticationResponseDto(String accessToken, String refreshToken, Long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserId() {
        return userId;
    }
}
