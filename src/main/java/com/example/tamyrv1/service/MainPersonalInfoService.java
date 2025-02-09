/*package com.example.tamyrv1.service;

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
*/
package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;

import java.util.List;

public interface MainPersonalInfoService {
    MainPersonalInfoDto save(MainPersonalInfoDto infoDto);
    MainPersonalInfoDto getById(int id);
    MainPersonalInfoDto getByUserId(Long userId); // Новый метод
    List<MainPersonalInfoDto> getAll();
    void delete(int id);
    void deleteByUserId(Long userId); // Новый метод
}
