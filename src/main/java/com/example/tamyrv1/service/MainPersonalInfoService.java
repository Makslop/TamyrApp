package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MainPersonalInfoService {
    List<MainPersonalInfoDto> getAll();
    MainPersonalInfoDto getById(int id);
    MainPersonalInfoDto save(MainPersonalInfoDto infoDto);
    void delete(int id);
}
