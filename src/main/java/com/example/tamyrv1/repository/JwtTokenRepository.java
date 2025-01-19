package com.example.tamyrv1.repository;
import com.example.tamyrv1.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String token);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);
}
