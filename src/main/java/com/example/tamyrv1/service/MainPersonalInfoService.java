package com.example.tamyrv1.service;


import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPersonalInfoService {

    private final MainPersonalInfoRepository repository;

    public MainPersonalInfoService(MainPersonalInfoRepository repository) {
        this.repository = repository;
    }

    public List<MainPersonalInfo> getAll() {
        return repository.findAll();
    }

    public MainPersonalInfo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("MainPersonalInfo not found"));
    }

    public MainPersonalInfo save(MainPersonalInfo info) {
        return repository.save(info);
    }

    public MainPersonalInfo update(Long id, MainPersonalInfo updatedInfo) {
        MainPersonalInfo existing = getById(id);
        existing.setAge(updatedInfo.getAge());
        existing.setSex(updatedInfo.getSex());
        existing.setDateOfBirth(updatedInfo.getDateOfBirth());
        existing.setWeight(updatedInfo.getWeight());
        existing.setHeight(updatedInfo.getHeight());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
