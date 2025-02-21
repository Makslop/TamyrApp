package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.MiBandData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MiBandDataRepository extends JpaRepository<MiBandData, Long> {
    List<MiBandData> findByUserId(Long userId);
}
