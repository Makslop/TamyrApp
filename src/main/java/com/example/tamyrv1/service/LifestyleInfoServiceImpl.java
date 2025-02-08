/*package com.example.tamyrv1.service;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.dto.LifestyleInfoDto;
import com.example.tamyrv1.repository.LifestyleInfoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class LifestyleInfoServiceImpl implements LifestyleInfoService {

    @Autowired
    private LifestyleInfoRepository lifestyleInfoRepository;

    @Override
    public LifestyleInfoDto saveLifestyleInfo(@Valid @NotNull LifestyleInfoDto lifestyleInfoDto) {
        validateLifestyleInfoDto(lifestyleInfoDto);
        LifestyleInfo lifestyleInfo = mapToEntity(lifestyleInfoDto);
        LifestyleInfo savedInfo = lifestyleInfoRepository.save(lifestyleInfo);
        return mapToDto(savedInfo);
    }

    @Override
    public LifestyleInfoDto getLifestyleInfoById(@NotNull Integer id) {
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
    public void deleteLifestyleInfo(@NotNull Integer id) {
        lifestyleInfoRepository.deleteById(id);
    }

    private LifestyleInfo mapToEntity(@Valid LifestyleInfoDto dto) {
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

    private void validateLifestyleInfoDto(LifestyleInfoDto dto) {
        if (dto.getUserId() == null || dto.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
        if (dto.getFruitIntake() == null || !(dto.getFruitIntake().equals("low") ||
                dto.getFruitIntake().equals("medium") || dto.getFruitIntake().equals("high"))) {
            throw new IllegalArgumentException("Fruit intake must be 'low', 'medium', or 'high'.");
        }
    }
}

 */
package com.example.tamyrv1.service;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.dto.LifestyleInfoDto;
import com.example.tamyrv1.repository.LifestyleInfoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class LifestyleInfoServiceImpl implements LifestyleInfoService {

    @Autowired
    private LifestyleInfoRepository lifestyleInfoRepository;

    @Override
    public LifestyleInfoDto saveLifestyleInfo(@Valid @NotNull LifestyleInfoDto lifestyleInfoDto) {
        LifestyleInfo lifestyleInfo = mapToEntity(lifestyleInfoDto);
        LifestyleInfo savedInfo = lifestyleInfoRepository.save(lifestyleInfo);
        return mapToDto(savedInfo);
    }

    @Override
    public LifestyleInfoDto getLifestyleInfoById(@NotNull Integer id) {
        LifestyleInfo lifestyleInfo = lifestyleInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lifestyle info not found for ID: " + id));
        return mapToDto(lifestyleInfo);
    }

    @Override
    public List<LifestyleInfoDto> getAllLifestyleInfo() {
        return lifestyleInfoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteLifestyleInfo(@NotNull Integer id) {
        lifestyleInfoRepository.deleteById(id);
    }

    private LifestyleInfo mapToEntity(@Valid LifestyleInfoDto dto) {
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

