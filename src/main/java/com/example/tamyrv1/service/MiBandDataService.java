package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MiBandDataDTO;
import com.example.tamyrv1.model.User;

import java.util.List;

public interface MiBandDataService {
    void saveMiBandData(User user, MiBandDataDTO miBandDataDTO);

    List<MiBandDataDTO> getAllMiBandDataByUser(Long userId);
}
