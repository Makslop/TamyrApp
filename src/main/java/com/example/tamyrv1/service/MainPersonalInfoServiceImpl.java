/*package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    @Autowired
    private MainPersonalInfoRepository repository;

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
        MainPersonalInfo entity = toEntity(infoDto);
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
                entity.getUserId(),
                entity.getAge(),
                entity.getSex(),
                entity.getWeight(),
                entity.getHeight()
        );
    }

    private MainPersonalInfo toEntity(MainPersonalInfoDto dto) {
        MainPersonalInfo entity = new MainPersonalInfo();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
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
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    @Autowired
    private MainPersonalInfoRepository repository;

    @Autowired
    private UserService userService; // Добавлено

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MainPersonalInfoDto getById(int id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Personal info not found for ID: " + id));
    }

    @Override
    public MainPersonalInfoDto save(MainPersonalInfoDto infoDto) {
        if (!userService.existsById(infoDto.getUserId())) {
            throw new IllegalArgumentException("User with ID " + infoDto.getUserId() + " does not exist.");
        }

        MainPersonalInfo entity = toEntity(infoDto);
        MainPersonalInfo savedEntity = repository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete, no record found with ID: " + id);
        }
        repository.deleteById(id);
    }

    private MainPersonalInfoDto toDto(MainPersonalInfo entity) {
        return new MainPersonalInfoDto(
                entity.getId(),
                entity.getUserId(),
                entity.getAge(),
                entity.getSex(),
                entity.getWeight(),
                entity.getHeight()
        );
    }

    private MainPersonalInfo toEntity(MainPersonalInfoDto dto) {
        MainPersonalInfo entity = new MainPersonalInfo();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
        return entity;
    }
}
