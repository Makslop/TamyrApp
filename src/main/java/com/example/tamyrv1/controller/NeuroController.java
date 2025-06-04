package com.example.tamyrv1.controller;

import com.example.tamyrv1.service.NeuroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/neuro")
public class NeuroController {

    @Autowired
    private NeuroService neuroService;

    @PostMapping("/predict")
    public ResponseEntity<String> predict(@RequestBody Map<String, Object> requestData) {
        return (ResponseEntity<String>) neuroService.forwardToDjango(requestData);
    }
}
