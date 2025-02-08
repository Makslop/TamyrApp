/*
package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    @Autowired
    private MainPersonalInfoRepository repository;

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MainPersonalInfoDto getById(@NotNull @Positive int id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() ->
                new IllegalArgumentException("User with ID " + id + " not found."));
    }

    @Override
    public MainPersonalInfoDto save(@Valid MainPersonalInfoDto infoDto) {
        validatePersonalInfo(infoDto);
        MainPersonalInfo entity = toEntity(infoDto);
        MainPersonalInfo savedEntity = repository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public void delete(@NotNull @Positive int id) {
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

    private void validatePersonalInfo(MainPersonalInfoDto dto) {
        if (dto.getUserId() == null || dto.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
        if (dto.getAge() < 0 || dto.getAge() > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120.");
        }
        if (!dto.getSex().matches("(?i)male|female")) {
            throw new IllegalArgumentException("Sex must be 'male' or 'female'.");
        }
        if (dto.getWeight() < 30 || dto.getWeight() > 300) {
            throw new IllegalArgumentException("Weight must be between 30kg and 300kg.");
        }
        if (dto.getHeight() < 50 || dto.getHeight() > 250) {
            throw new IllegalArgumentException("Height must be between 50cm and 250cm.");
        }
    }
}
*/
package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class MainPersonalInfoServiceImpl implements MainPersonalInfoService {

    @Autowired
    private MainPersonalInfoRepository repository;

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MainPersonalInfoDto getById(@NotNull @Positive int id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() ->
                new IllegalArgumentException("User with ID " + id + " not found."));
    }

    @Override
    public MainPersonalInfoDto save(@Valid MainPersonalInfoDto infoDto) {
        MainPersonalInfo entity = toEntity(infoDto);
        MainPersonalInfo savedEntity = repository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public void delete(@NotNull @Positive int id) {
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

