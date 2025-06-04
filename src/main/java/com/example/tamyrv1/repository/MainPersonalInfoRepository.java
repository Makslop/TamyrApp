package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainPersonalInfoRepository extends JpaRepository<MainPersonalInfo, Long> {

    Optional<MainPersonalInfo> findByUser(User user);
    Optional<MainPersonalInfo> findByUserId(Long userId);

}
