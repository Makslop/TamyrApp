package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.AuthenticationResponseDto;
import com.example.tamyrv1.dto.LoginRequestDto;
import com.example.tamyrv1.dto.RegistrationRequestDto;
import com.example.tamyrv1.service.AuthenticationService;
import com.example.tamyrv1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;


    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(
            @RequestBody @Valid RegistrationRequestDto registrationRequestDto
            ){

        if(userService.existsByEmail(registrationRequestDto.getUsername())){
            return ResponseEntity.badRequest().body("Error 1");
        }

        authenticationService.register(registrationRequestDto);
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody @Valid LoginRequestDto request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        return authenticationService.refreshToken(request,response);
    }

}
