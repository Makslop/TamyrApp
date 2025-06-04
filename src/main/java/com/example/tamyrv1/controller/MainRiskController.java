package com.example.tamyrv1.controller;

import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import com.example.tamyrv1.service.NeuroStimulatorsPredictionService;
import com.example.tamyrv1.service.RiskPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/risk")
public class MainRiskController {

    private final RiskPredictionService riskPredictionService;
    private final NeuroStimulatorsPredictionService neuroStimulatorsPredictionService;
    private final MainPersonalInfoRepository infoRepository;

    public MainRiskController(RiskPredictionService riskPredictionService,
                              NeuroStimulatorsPredictionService neuroStimulatorsPredictionService,
                              MainPersonalInfoRepository infoRepository) {
        this.riskPredictionService = riskPredictionService;
        this.neuroStimulatorsPredictionService = neuroStimulatorsPredictionService;
        this.infoRepository = infoRepository;
    }

    // Первый эндпоинт — общий риск (молодые/старики)
    @GetMapping("/predict")
    public ResponseEntity<?> predictRisk(@RequestParam Long userId) {
        try {
            MainPersonalInfo info = infoRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("User profile not found"));

            Map<String, Object> result = riskPredictionService.predictRisk(userId, info.getAge());

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Второй эндпоинт — нейростимуляторы, сбор данных за 7 дней и запрос к Django
    @GetMapping("/neurostimulator_predict")
    public ResponseEntity<?> predictNeurostimulatorRisk(@RequestParam Long userId) {
        try {
            MainPersonalInfo info = infoRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("User profile not found"));

            Map<String, Object> result = neuroStimulatorsPredictionService.preparePredictionData(userId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
