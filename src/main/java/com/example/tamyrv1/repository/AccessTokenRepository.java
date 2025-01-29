package com.example.tamyrv1.repository;


import com.example.tamyrv1.model.AccessToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByToken(String token);

    void deleteByToken(String token);

    @Query("SELECT a FROM AccessToken a WHERE a.user.id = :userId AND a.loggedOut = false")
    List<AccessToken> findValidTokensByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE AccessToken a SET a.loggedOut = true WHERE a.user.id = :userId")
    void revokeAllTokens(@Param("userId") long userId);
}
