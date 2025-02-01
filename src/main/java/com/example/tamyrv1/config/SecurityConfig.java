package com.example.tamyrv1.config;


import com.example.tamyrv1.filter.JwtFilter;
import com.example.tamyrv1.handler.CustomAccessDeniedHandler;
import com.example.tamyrv1.handler.CustomLogoutHandler;
import com.example.tamyrv1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final UserService userService;

    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final CustomLogoutHandler customLogoutHandler;

    public SecurityConfig(JwtFilter jwtFilter,
                          UserService userService,
                          CustomAccessDeniedHandler accessDeniedHandler,
                          CustomLogoutHandler customLogoutHandler) {

        this.jwtFilter = jwtFilter;
        this.userService = userService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.customLogoutHandler = customLogoutHandler;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login/**", "/registration/**", "/css/**", "/refresh_token/**", "/")
                            .permitAll(); // Разрешаем все запросы к этим URL
                    auth.requestMatchers("/admin/**").hasAuthority("ADMIN"); // Разрешаем запросы только для администратора
                    auth.anyRequest().authenticated(); // Требуем аутентификацию для всех остальных запросов
                }).userDetailsService(userService)
                .exceptionHandling(e -> {
                    e.accessDeniedHandler(accessDeniedHandler); // Обработчик отказа доступа
                    e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)); // Входная точка аутентификации
                })
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Управление сессиями
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Добавление фильтра JWT перед фильтром UsernamePasswordAuthenticationFilter
                .logout(log -> {
                    log.logoutUrl("/logout"); // URL для выхода
                    log.addLogoutHandler(customLogoutHandler); // Добавление пользовательского обработчика выхода
                    log.logoutSuccessHandler((request, response, authentication) ->
                            SecurityContextHolder.clearContext()); // Очистка контекста безопасности после успешного выхода
                });

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
