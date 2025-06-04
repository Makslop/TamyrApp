package com.example.tamyrv1.service;

import com.example.tamyrv1.model.UserRiskLevel;
import com.example.tamyrv1.repository.UserRiskLevelRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RiskPredictionService {

    private final YoungStatisticalPredictionService youngService;
    private final ElderlyStatisticalPredictionService elderlyService;
    private final UserRiskLevelRepository riskRepository;

    public RiskPredictionService(YoungStatisticalPredictionService youngService,
                                 ElderlyStatisticalPredictionService elderlyService,
                                 UserRiskLevelRepository riskRepository) {
        this.youngService = youngService;
        this.elderlyService = elderlyService;
        this.riskRepository = riskRepository;
    }

    public Map<String, Object> predictRisk(Long userId, int age) {
        Map<String, Object> predictionResult;
        if (age < 50) {
            predictionResult = youngService.prepareYoungPrediction(userId);
        } else {
            predictionResult = elderlyService.prepareElderlyPrediction(userId);
        }

        String djangoResponse = (String) predictionResult.get("django_response");
        double probability = parseProbability(djangoResponse);

        String riskLevel = convertProbabilityToRiskLevel(probability);

        UserRiskLevel userRisk = riskRepository.findByUserId(userId).orElse(new UserRiskLevel());
        userRisk.setUserId(userId);
        userRisk.setRiskProbability(probability);
        userRisk.setRiskLevel(riskLevel); // Now in English
        riskRepository.save(userRisk);

        return Map.of(
                "probability", probability,
                "riskLevel", riskLevel,
                "rawResponse", djangoResponse
        );
    }

    private double parseProbability(String jsonResponse) {
        try {
            com.fasterxml.jackson.databind.JsonNode node = new com.fasterxml.jackson.databind.ObjectMapper().readTree(jsonResponse);
            if (node.has("probability")) {
                return node.get("probability").asDouble();
            }
            if (node.has("prob")) {
                return node.get("prob").asDouble();
            }
        } catch (Exception e) {
            // Log the error if needed
        }
        return 0.0;
    }

    private String convertProbabilityToRiskLevel(double p) {
        if (p < 0.33) return "low";
        if (p < 0.66) return "medium";
        return "high";
    }
}
