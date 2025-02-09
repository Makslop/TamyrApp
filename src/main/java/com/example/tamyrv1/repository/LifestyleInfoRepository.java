package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LifestyleInfoRepository extends JpaRepository<LifestyleInfo, Integer> {

    // Находит LifestyleInfo по User
    Optional<LifestyleInfo> findByUser(User user);

    // Удаляет LifestyleInfo по User
    void deleteByUser(User user);
}
