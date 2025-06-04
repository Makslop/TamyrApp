package com.example.tamyrv1.controller;// 5. NeuroStimulatorsPredictionController.java

import com.example.tamyrv1.service.NeuroStimulatorsPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/predict")
public class NeuroStimulatorsPredictionController {

    private final NeuroStimulatorsPredictionService predictionService;

    public NeuroStimulatorsPredictionController(NeuroStimulatorsPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/weekly")
    public ResponseEntity<?> getPredictionData(@RequestParam Long userId) {
        try {
            Map<String, Object> payload = predictionService.preparePredictionData(userId);
            return ResponseEntity.ok(payload);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
