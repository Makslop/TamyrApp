package com.example.tamyrv1.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class RedisTokenService {

    private final StringRedisTemplate redisTemplate;

    public RedisTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Сохранение access-токена в Redis (по userId)
    public void saveAccessToken(String userName, String accessToken) {
        String key = "access_token:" + userName;
        redisTemplate.opsForValue().set(key, accessToken, Duration.ofHours(10)); // Храним 10 часов
    }

    // Получение access-токена из Redis
    public String getAccessToken(String userName) {
        String key = "access_token:" + userName;
        return redisTemplate.opsForValue().get(key);
    }

    // Удаление access-токена из Redis (например, при логауте)
    public void deleteAccessToken(String userName) {
        String key = "access_token:" + userName;
        redisTemplate.delete(key);
    }
}
