package com.example.tamyrv1.repository;
import com.example.tamyrv1.model.LifestyleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifestyleInfoRepository extends JpaRepository<LifestyleInfo, Long> {
}
