package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LifestyleInfoRepository extends JpaRepository<LifestyleInfo, Long> {

    Optional<LifestyleInfo> findByUser(User user);

    void deleteByUser(User user);

}
