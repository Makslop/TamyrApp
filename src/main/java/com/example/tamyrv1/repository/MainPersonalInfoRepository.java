package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.MainPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainPersonalInfoRepository extends JpaRepository<MainPersonalInfo, Integer> {
    // Здесь можно добавить кастомные запросы, если нужно
}
