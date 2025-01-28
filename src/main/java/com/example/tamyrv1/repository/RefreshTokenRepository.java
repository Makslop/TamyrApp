package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

    @Query("SELECT r FROM RefreshToken r WHERE r.user.id = :userId AND r.revoked = false")
    List<RefreshToken> findValidTokensByUserId(@Param("userId") Long userId);
}
