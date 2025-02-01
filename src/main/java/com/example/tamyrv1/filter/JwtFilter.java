package com.example.tamyrv1.filter;

import com.example.tamyrv1.service.JwtService;
import com.example.tamyrv1.service.RedisTokenService;
import com.example.tamyrv1.service.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter extends OncePerRequestFilter {
        private final JwtService jwtService;
        private final UserService userService;
        private final RedisTokenService redisTokenService;

        public JwtFilter(JwtService jwtService, UserService userService, RedisTokenService redisTokenService) {
            this.jwtService = jwtService;
            this.userService = userService;
            this.redisTokenService = redisTokenService;
        }

        @Override
        protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);

                // 1️⃣ Проверяем сначала в Redis
                try {
                    String cachedToken = redisTokenService.getAccessToken(username);
                    if (cachedToken != null && cachedToken.equals(token)) {
                        System.out.println("REDIS IS WORK");
                        setAuthentication(userDetails, request);
                    } else {
                        // 2️⃣ Если в Redis нет, проверяем в PostgreSQL
                        if (jwtService.isValidAccess(token, userDetails)) {
                            setAuthentication(userDetails, request);
                        }
                    }
                } catch (Exception e) {
                    // 3️⃣ Если Redis недоступен – проверяем в PostgreSQL
                    if (jwtService.isValidAccess(token, userDetails)) {
                        setAuthentication(userDetails, request);
                    }
                }
            }

            filterChain.doFilter(request, response);
        }

        private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

