/*package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    private final MainPersonalInfoRepository repository;
    private final UserServiceImpl userService;

    @Autowired
    public MainPersonalInfoServiceImpl(MainPersonalInfoRepository repository, UserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MainPersonalInfoDto getById(int id) {
        return repository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public MainPersonalInfoDto save(MainPersonalInfoDto infoDto) {
        // Проверяем, существует ли пользователь с таким userId
        User user = userService.getUserById(infoDto.getUserId());

        // Преобразуем DTO в сущность
        MainPersonalInfo entity = toEntity(infoDto);
        entity.setUser(user); // Устанавливаем пользователя

        // Сохраняем в БД
        MainPersonalInfo savedEntity = repository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    // Маппинг DTO в Entity
    private MainPersonalInfoDto toDto(MainPersonalInfo entity) {
        return new MainPersonalInfoDto(
                entity.getId(),
                entity.getUser().getId(), // Теперь берем userId из User
                entity.getAge(),
                entity.getSex(),
                entity.getWeight(),
                entity.getHeight()
        );
    }

    private MainPersonalInfo toEntity(MainPersonalInfoDto dto) {
        MainPersonalInfo entity = new MainPersonalInfo();
        entity.setId(dto.getId());
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
        return entity;
    }
}
*/
package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    private final MainPersonalInfoRepository repository;
    private final UserServiceImpl userService;

    @Autowired
    public MainPersonalInfoServiceImpl(MainPersonalInfoRepository repository, UserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public MainPersonalInfoDto save(MainPersonalInfoDto infoDto) {
        // Проверяем, существует ли пользователь с таким userId
        User user = userService.getUserById(infoDto.getUserId());

        // Проверяем, есть ли уже запись MainPersonalInfo для данного пользователя
        Optional<MainPersonalInfo> existingInfo = repository.findByUser(user);

        MainPersonalInfo personalInfo;
        if (existingInfo.isPresent()) {
            // Если запись уже существует, обновляем её
            personalInfo = existingInfo.get();
            updateMainPersonalInfoFromDto(personalInfo, infoDto);
        } else {
            // Если записи нет, создаем новую
            personalInfo = new MainPersonalInfo(user, infoDto.getAge(), infoDto.getSex(),
                    infoDto.getWeight(), infoDto.getHeight());
        }

        // Сохраняем в БД
        MainPersonalInfo savedEntity = repository.save(personalInfo);
        return toDto(savedEntity);
    }

    @Override
    public MainPersonalInfoDto getById(int id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Main personal info not found for ID: " + id));
    }

    @Override
    public MainPersonalInfoDto getByUserId(Long userId) {
        User user = userService.getUserById(userId);
        MainPersonalInfo personalInfo = repository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Main personal info not found for user ID: " + userId));
        return toDto(personalInfo);
    }

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long userId) {
        User user = userService.getUserById(userId);
        repository.deleteByUser(user);
    }

    // Маппинг Entity -> DTO
    private MainPersonalInfoDto toDto(MainPersonalInfo entity) {
        return new MainPersonalInfoDto(
                entity.getId(),
                entity.getUser().getId(),
                entity.getAge(),
                entity.getSex(),
                entity.getWeight(),
                entity.getHeight()
        );
    }

    // Метод для обновления существующей записи из DTO
    private void updateMainPersonalInfoFromDto(MainPersonalInfo entity, MainPersonalInfoDto dto) {
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
    }
}
