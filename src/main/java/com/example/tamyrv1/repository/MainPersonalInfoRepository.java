package com.example.tamyrv1.repository;
import com.example.tamyrv1.model.MainPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MainPersonalInfoRepository extends JpaRepository<MainPersonalInfo, Long> {
}
