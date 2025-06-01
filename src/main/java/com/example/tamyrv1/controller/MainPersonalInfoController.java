package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.MainPersonalInfoDto;
import com.example.tamyrv1.service.MainPersonalInfoServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-info")
public class MainPersonalInfoController {

    private final MainPersonalInfoServiceImpl personalInfoService;

    public MainPersonalInfoController(MainPersonalInfoServiceImpl personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @PostMapping
    public ResponseEntity<MainPersonalInfoDto> createPersonalInfo(@Valid @RequestBody MainPersonalInfoDto infoDto) {
        return ResponseEntity.ok(personalInfoService.save(infoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainPersonalInfoDto> getPersonalInfoById(
            @PathVariable @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok(personalInfoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<MainPersonalInfoDto>> getAllPersonalInfo() {
        return ResponseEntity.ok(personalInfoService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalInfo(
            @PathVariable @Positive(message = "ID must be a positive number") Long id) {
        personalInfoService.delete(id);
        return ResponseEntity.ok("Personal info with ID " + id + " has been deleted.");
    }
    @GetMapping("/age/{userId}")
    public ResponseEntity<Integer> getUserAge(@PathVariable Long userId) {
        int age = personalInfoService.getAgeByUserId(userId);
        return ResponseEntity.ok(age);
    }

}
