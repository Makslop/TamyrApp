package com.example.tamyrv1.service;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.repository.LifestyleInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifestyleInfoService {

    private final LifestyleInfoRepository repository;

    public LifestyleInfoService(LifestyleInfoRepository repository) {
        this.repository = repository;
    }

    public List<LifestyleInfo> getAll() {
        return repository.findAll();
    }

    public LifestyleInfo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("LifestyleInfo not found"));
    }

    public LifestyleInfo save(LifestyleInfo info) {
        return repository.save(info);
    }

    public LifestyleInfo update(Long id, LifestyleInfo updatedInfo) {
        LifestyleInfo existing = getById(id);
        existing.setSmokes(updatedInfo.getSmokes());
        existing.setDrinksAlcohol(updatedInfo.getDrinksAlcohol());
        existing.setExercises(updatedInfo.getExercises());
        existing.setFruitIntake(updatedInfo.getFruitIntake());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
