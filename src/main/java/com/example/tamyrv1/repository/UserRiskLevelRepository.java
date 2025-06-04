package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.UserRiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRiskLevelRepository extends JpaRepository<UserRiskLevel, Long> {
    Optional<UserRiskLevel> findByUserId(Long userId);
}
