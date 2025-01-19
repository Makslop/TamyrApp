package com.example.tamyrv1.controller;

import com.example.tamyrv1.model.LifestyleInfo;
import com.example.tamyrv1.service.LifestyleInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lifestyle-info")
public class LifestyleInfoController {

    private final LifestyleInfoService service;

    public LifestyleInfoController(LifestyleInfoService service) {
        this.service = service;
    }

    @GetMapping
    public List<LifestyleInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LifestyleInfo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public LifestyleInfo create(@RequestBody LifestyleInfo info) {
        return service.save(info);
    }

    @PutMapping("/{id}")
    public LifestyleInfo update(@PathVariable Long id, @RequestBody LifestyleInfo info) {
        return service.update(id, info);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
