package com.example.tamyrv1.controller;

import com.example.tamyrv1.service.ElderlyStatisticalPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/elderly_prediction")
public class ElderlyStatisticalPredictionController {

    private final ElderlyStatisticalPredictionService predictionService;

    public ElderlyStatisticalPredictionController(ElderlyStatisticalPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/predict")
    public ResponseEntity<?> getPrediction(@RequestParam Long userId) {
        try {
            Map<String, Object> result = predictionService.prepareElderlyPrediction(userId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
