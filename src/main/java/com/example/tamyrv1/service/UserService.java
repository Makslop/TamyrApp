package com.example.tamyrv1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService{
    boolean existsByEmail(String email);
    List<Long> getAllUserIds();
}
