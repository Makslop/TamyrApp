package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.LifestyleInfoDto;
import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.LifestyleInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LifestyleInfoServiceImpl implements LifestyleInfoService {

    private final LifestyleInfoRepository lifestyleInfoRepository;
    private final UserServiceImpl userService;

    @Autowired
    public LifestyleInfoServiceImpl(LifestyleInfoRepository lifestyleInfoRepository, UserServiceImpl userService) {
        this.lifestyleInfoRepository = lifestyleInfoRepository;
        this.userService = userService;
    }
    @Transactional
    @Override
    public LifestyleInfoDto saveLifestyleInfo(LifestyleInfoDto lifestyleInfoDto) {
        User user = userService.getUserById(lifestyleInfoDto.getUserId());
        Optional<LifestyleInfo> existingInfo = lifestyleInfoRepository.findByUser(user);
        LifestyleInfo lifestyleInfo;
        if (existingInfo.isPresent()) {
            lifestyleInfo = existingInfo.get();
            updateLifestyleInfoFromDto(lifestyleInfo, lifestyleInfoDto);
        } else {
            lifestyleInfo = new LifestyleInfo(user, lifestyleInfoDto.getSmokes(), lifestyleInfoDto.getDrinksAlcohol(),
                    lifestyleInfoDto.getExercises(), lifestyleInfoDto.getFruitIntake());
        }

        LifestyleInfo savedInfo = lifestyleInfoRepository.save(lifestyleInfo);
        return mapToDto(savedInfo);
    }

    @Override
    public LifestyleInfoDto getLifestyleInfoById(Long id) {
        LifestyleInfo lifestyleInfo = lifestyleInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lifestyle info not found for ID: " + id));
        return mapToDto(lifestyleInfo);
    }

    @Override
    public List<LifestyleInfoDto> getAllLifestyleInfo() {
        return lifestyleInfoRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteLifestyleInfo(Long id) {
        lifestyleInfoRepository.deleteById(id);
    }

    private LifestyleInfoDto mapToDto(LifestyleInfo entity) {
        return new LifestyleInfoDto(
                entity.getLifestyleId(),
                entity.getUser().getId(),
                entity.isSmokes(),
                entity.isDrinksAlcohol(),
                entity.isExercises(),
                entity.getFruitIntake()
        );
    }

    private void updateLifestyleInfoFromDto(LifestyleInfo lifestyleInfo, LifestyleInfoDto dto) {
        lifestyleInfo.setSmokes(dto.getSmokes());
        lifestyleInfo.setDrinksAlcohol(dto.getDrinksAlcohol());
        lifestyleInfo.setExercises(dto.getExercises());
        lifestyleInfo.setFruitIntake(dto.getFruitIntake());
    }
}
