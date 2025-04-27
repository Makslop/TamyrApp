package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.AuthenticationResponseDto;
import com.example.tamyrv1.dto.LoginRequestDto;
import com.example.tamyrv1.dto.RegistrationRequestDto;
import com.example.tamyrv1.model.AccessToken;
import com.example.tamyrv1.model.RefreshToken;
import com.example.tamyrv1.model.Role;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.AccessTokenRepository;
import com.example.tamyrv1.repository.RefreshTokenRepository;
import com.example.tamyrv1.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final AccessTokenRepository accessTokenRepository;

    private final RefreshTokenRepository refreshTokenRepository;


    public AuthenticationService(UserRepository userRepository,
                                 JwtService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 RefreshTokenRepository refreshTokenRepository,
                                 AccessTokenRepository accessTokenRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenRepository = accessTokenRepository;
    }
    public void register(RegistrationRequestDto request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user = userRepository.save(user);
     }

     private void revokeAllToken(User user){
         accessTokenRepository.revokeAllTokens(user.getId());
         refreshTokenRepository.revokeAllTokens(user.getId());
     }

     private void saveUserToken(String accessToken, String refreshToken, User user){
         RefreshToken rToken = new RefreshToken();
         AccessToken aToken = new AccessToken();
         rToken.setToken(refreshToken);
         rToken.setUser(user);
         rToken.setRevoked(false);
         //rToken.setExpiryDate(LocalDateTime.from(Instant.now().plus(30, ChronoUnit.DAYS))); // Добавить дату истечения
         rToken.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS)
                 .atZone(ZoneId.systemDefault())
                 .toLocalDateTime()); // Исправлено
         aToken.setToken(accessToken);
         aToken.setUser(user);
         aToken.setLoggedOut(false);
         //aToken.setExpiryDate(LocalDateTime.from(Instant.now().plus(30, ChronoUnit.DAYS))); // Добавить дату истечения
         aToken.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS)
                 .atZone(ZoneId.systemDefault())
                 .toLocalDateTime()); // Исправлено

         refreshTokenRepository.save(rToken);
         accessTokenRepository.save(aToken);
     }
     public AuthenticationResponseDto authenticate(LoginRequestDto requestDto){
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         requestDto.getUsername(),
                         requestDto.getPassword()
                 )
         );

         User user = userRepository.findByEmail(requestDto.getUsername()).orElseThrow();

         String accessToken = jwtService.generateAccessToken(user);
         String refreshToken = jwtService.generateRefreshToken(user);

         revokeAllToken(user);
         saveUserToken(accessToken, refreshToken, user);

         // ✅ Возвращаем userId
         return new AuthenticationResponseDto(accessToken, refreshToken, user.getId());
     }

    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorizationHeader.substring(7);

        String username = jwtService.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user found"));

        if(jwtService.isValidRefresh(token,user)){
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllToken(user);

            saveUserToken(accessToken, refreshToken, user);
            /*
            return  new ResponseEntity<>(new AuthenticationResponseDto(accessToken,refreshToken), HttpStatus.OK);
             */
            return new ResponseEntity<>(new AuthenticationResponseDto(accessToken, refreshToken, user.getId()), HttpStatus.OK);

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}