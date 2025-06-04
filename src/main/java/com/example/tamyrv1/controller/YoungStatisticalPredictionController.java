package com.example.tamyrv1.controller;

import com.example.tamyrv1.service.YoungStatisticalPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/young_prediction")
public class YoungStatisticalPredictionController {

    private final YoungStatisticalPredictionService predictionService;

    public YoungStatisticalPredictionController(YoungStatisticalPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/predict")
    public ResponseEntity<?> getPrediction(@RequestParam Long userId) {
        try {
            Map<String, Object> result = predictionService.prepareYoungPrediction(userId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
