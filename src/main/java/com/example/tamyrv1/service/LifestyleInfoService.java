package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.LifestyleInfoDto;
import java.util.List;

public interface LifestyleInfoService {
    LifestyleInfoDto saveLifestyleInfo(LifestyleInfoDto lifestyleInfoDto);
    LifestyleInfoDto getLifestyleInfoById(Integer id);
    List<LifestyleInfoDto> getAllLifestyleInfo();
    void deleteLifestyleInfo(Integer id);
}