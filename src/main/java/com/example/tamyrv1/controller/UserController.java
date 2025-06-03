package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.UserDto;
import com.example.tamyrv1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(new UserDto(user.getEmail()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
