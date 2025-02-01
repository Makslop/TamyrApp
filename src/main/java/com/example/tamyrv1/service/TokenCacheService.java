package com.example.tamyrv1.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class TokenCacheService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long EXPIRATION_TIME = 10; // Время жизни токена в Redis (в минутах)

    public TokenCacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Сохранение токена в Redis
    public void saveAccessToken(String token, String userId) {
        redisTemplate.opsForValue().set("TOKEN:" + token, userId, EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    // Проверка токена в Redis
    public String getUserIdByToken(String token) {
        return redisTemplate.opsForValue().get("TOKEN:" + token);
    }

    // Удаление токена (при логауте)
    public void deleteAccessToken(String token) {
        redisTemplate.delete("TOKEN:" + token);
    }
}
