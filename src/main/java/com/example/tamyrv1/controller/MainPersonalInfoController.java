package com.example.tamyrv1.controller;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.service.MainPersonalInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main-personal-info")
public class MainPersonalInfoController {

    private final MainPersonalInfoService service;

    public MainPersonalInfoController(MainPersonalInfoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MainPersonalInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MainPersonalInfo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public MainPersonalInfo create(@RequestBody MainPersonalInfo info) {
        return service.save(info);
    }

    @PutMapping("/{id}")
    public MainPersonalInfo update(@PathVariable Long id, @RequestBody MainPersonalInfo info) {
        return service.update(id, info);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
