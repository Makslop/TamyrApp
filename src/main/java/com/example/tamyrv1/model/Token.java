package com.example.tamyrv1.model;

import java.time.LocalDateTime;

public interface Token {
    String getToken();
    void setToken(String token);
    LocalDateTime getExpiryDate();
    void setExpiryDate(LocalDateTime expiryDate);
}
