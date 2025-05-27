package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.LifestyleInfoDto;
import com.example.tamyrv1.service.LifestyleInfoServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lifestyle-info")
public class LifestyleInfoController {

    private final LifestyleInfoServiceImpl lifestyleInfoService;

    public LifestyleInfoController(LifestyleInfoServiceImpl lifestyleInfoService) {
        this.lifestyleInfoService = lifestyleInfoService;
    }

    @PostMapping
    public ResponseEntity<LifestyleInfoDto> createLifestyleInfo(@Valid @RequestBody LifestyleInfoDto lifestyleInfoDto) {
        LifestyleInfoDto createdInfo = lifestyleInfoService.saveLifestyleInfo(lifestyleInfoDto);
        return ResponseEntity.ok(createdInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LifestyleInfoDto> getLifestyleInfoById(@PathVariable @Positive(message = "ID must be a positive number") Long id) {
        LifestyleInfoDto lifestyleInfo = lifestyleInfoService.getLifestyleInfoById(id);
        return ResponseEntity.ok(lifestyleInfo);
    }

    @GetMapping
    public ResponseEntity<List<LifestyleInfoDto>> getAllLifestyleInfo() {
        List<LifestyleInfoDto> lifestyleInfos = lifestyleInfoService.getAllLifestyleInfo();
        return ResponseEntity.ok(lifestyleInfos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLifestyleInfo(@PathVariable @Positive(message = "ID must be a positive number") Long id) {
        lifestyleInfoService.deleteLifestyleInfo(id);
        return ResponseEntity.ok("Lifestyle info with ID " + id + " has been deleted successfully.");
    }
}
