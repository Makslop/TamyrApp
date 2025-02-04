package com.example.tamyrv1.service;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.dto.LifestyleInfoDto;
import com.example.tamyrv1.repository.LifestyleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LifestyleInfoServiceImpl implements LifestyleInfoService {

    @Autowired
    private LifestyleInfoRepository lifestyleInfoRepository;

    @Override
    public LifestyleInfoDto saveLifestyleInfo(LifestyleInfoDto lifestyleInfoDto) {
        LifestyleInfo lifestyleInfo = mapToEntity(lifestyleInfoDto);
        LifestyleInfo savedInfo = lifestyleInfoRepository.save(lifestyleInfo);
        return mapToDto(savedInfo);
    }

    @Override
    public LifestyleInfoDto getLifestyleInfoById(Integer id) {
        LifestyleInfo lifestyleInfo = lifestyleInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lifestyle info not found for ID: " + id));
        return mapToDto(lifestyleInfo);
    }

    @Override
    public List<LifestyleInfoDto> getAllLifestyleInfo() {
        List<LifestyleInfo> infos = lifestyleInfoRepository.findAll();
        return infos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteLifestyleInfo(Integer id) {
        lifestyleInfoRepository.deleteById(id);
    }

    private LifestyleInfo mapToEntity(LifestyleInfoDto dto) {
        LifestyleInfo lifestyleInfo = new LifestyleInfo();
        lifestyleInfo.setUserId(dto.getUserId());
        lifestyleInfo.setSmokes(dto.isSmokes());
        lifestyleInfo.setDrinksAlcohol(dto.isDrinksAlcohol());
        lifestyleInfo.setExercises(dto.isExercises());
        lifestyleInfo.setFruitIntake(dto.getFruitIntake());
        return lifestyleInfo;
    }

    private LifestyleInfoDto mapToDto(LifestyleInfo entity) {
        LifestyleInfoDto dto = new LifestyleInfoDto();
        dto.setUserId(entity.getUserId());
        dto.setSmokes(entity.isSmokes());
        dto.setDrinksAlcohol(entity.isDrinksAlcohol());
        dto.setExercises(entity.isExercises());
        dto.setFruitIntake(entity.getFruitIntake());
        return dto;
    }
}
