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
        // Проверяем, существует ли пользователь с таким userId
        User user = userService.getUserById(lifestyleInfoDto.getUserId());

        // Проверяем, есть ли уже запись LifestyleInfo для данного пользователя
        Optional<LifestyleInfo> existingInfo = lifestyleInfoRepository.findByUser(user);

        LifestyleInfo lifestyleInfo;
        if (existingInfo.isPresent()) {
            // Если запись уже существует, обновляем её
            lifestyleInfo = existingInfo.get();
            updateLifestyleInfoFromDto(lifestyleInfo, lifestyleInfoDto);
        } else {
            // Если записи нет, создаём новую
            lifestyleInfo = new LifestyleInfo(user, lifestyleInfoDto.getSmokes(), lifestyleInfoDto.getDrinksAlcohol(),
                    lifestyleInfoDto.getExercises(), lifestyleInfoDto.getFruitIntake());
        }

        // Сохраняем в БД (если обновляем — Hibernate автоматически обновит запись)
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
    public LifestyleInfoDto getLifestyleInfoByUserId(Long userId) {
        User user = userService.getUserById(userId);
        LifestyleInfo lifestyleInfo = lifestyleInfoRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Lifestyle info not found for user ID: " + userId));
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
    public void deleteLifestyleInfo(Integer id) {
        lifestyleInfoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteLifestyleInfoByUserId(Long userId) {
        User user = userService.getUserById(userId);
        lifestyleInfoRepository.deleteByUser(user);
    }

    // Метод для преобразования сущности в DTO
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

    // Метод для обновления существующей записи `LifestyleInfo` из DTO
    private void updateLifestyleInfoFromDto(LifestyleInfo lifestyleInfo, LifestyleInfoDto dto) {
        lifestyleInfo.setSmokes(dto.getSmokes());
        lifestyleInfo.setDrinksAlcohol(dto.getDrinksAlcohol());
        lifestyleInfo.setExercises(dto.getExercises());
        lifestyleInfo.setFruitIntake(dto.getFruitIntake());
    }
}
