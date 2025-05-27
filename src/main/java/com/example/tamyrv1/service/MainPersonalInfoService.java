package com.example.tamyrv1.service;
import com.example.tamyrv1.dto.MainPersonalInfoDto;
import java.util.List;

public interface MainPersonalInfoService {
    MainPersonalInfoDto save(MainPersonalInfoDto infoDto);
    MainPersonalInfoDto getById(Long id);
    List<MainPersonalInfoDto> getAll();
    void delete(Long id);
}
