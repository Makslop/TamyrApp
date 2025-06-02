/*package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public MainPersonalInfoDto save(MainPersonalInfoDto infoDto) {
        User user = userService.getUserById(infoDto.getUserId());

        Optional<MainPersonalInfo> existingInfo = repository.findByUser(user);

        MainPersonalInfo personalInfo;
        if (existingInfo.isPresent()) {
            personalInfo = existingInfo.get();
            updateMainPersonalInfoFromDto(personalInfo, infoDto);
        } else {
            personalInfo = new MainPersonalInfo(user, infoDto.getAge(), infoDto.getSex(),
                    infoDto.getWeight(), infoDto.getHeight());
        }

        MainPersonalInfo savedEntity = repository.save(personalInfo);
        return toDto(savedEntity);
    }

    @Override
    public MainPersonalInfoDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Main personal info not found for ID: " + id));
    }

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

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

    private void updateMainPersonalInfoFromDto(MainPersonalInfo entity, MainPersonalInfoDto dto) {
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
    }
}
*/
package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public MainPersonalInfoDto save(MainPersonalInfoDto infoDto) {
        User user = userService.getUserById(infoDto.getUserId());

        Optional<MainPersonalInfo> existingInfo = repository.findByUser(user);

        MainPersonalInfo personalInfo;
        if (existingInfo.isPresent()) {
            personalInfo = existingInfo.get();
            updateMainPersonalInfoFromDto(personalInfo, infoDto);
        } else {
            personalInfo = new MainPersonalInfo(
                    user,
                    infoDto.getName(),
                    infoDto.getSurname(),
                    infoDto.getAge(),
                    infoDto.getSex(),
                    infoDto.getWeight(),
                    infoDto.getHeight()
            );
        }

        MainPersonalInfo savedEntity = repository.save(personalInfo);
        return toDto(savedEntity);
    }

    @Override
    public MainPersonalInfoDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Main personal info not found for ID: " + id));
    }

    @Override
    public List<MainPersonalInfoDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private MainPersonalInfoDto toDto(MainPersonalInfo entity) {
        return new MainPersonalInfoDto(
                entity.getId(),
                entity.getUser().getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getAge(),
                entity.getSex(),
                entity.getWeight(),
                entity.getHeight()
        );
    }
    @Override
    public int getAgeByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return repository.findByUser(user)
                .map(MainPersonalInfo::getAge)
                .orElseThrow(() -> new RuntimeException("Age not found for userId " + userId));
    }
    @Override
    public boolean existsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return repository.findByUser(user).isPresent();
    }



    private void updateMainPersonalInfoFromDto(MainPersonalInfo entity, MainPersonalInfoDto dto) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setWeight(dto.getWeight());
        entity.setHeight(dto.getHeight());
    }
}
