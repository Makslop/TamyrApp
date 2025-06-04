package com.example.tamyrv1.service;

import com.example.tamyrv1.model.Answers;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.model.UserRiskLevel;
import com.example.tamyrv1.repository.AnswersRepository;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import com.example.tamyrv1.repository.UserRiskLevelRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NeuroStimulatorsPredictionService {

    private final AnswersRepository answersRepository;
    private final MainPersonalInfoRepository infoRepository;
    private final UserRiskLevelRepository riskLevelRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public NeuroStimulatorsPredictionService(AnswersRepository answersRepository,
                                             MainPersonalInfoRepository infoRepository,
                                             UserRiskLevelRepository riskLevelRepository) {
        this.answersRepository = answersRepository;
        this.infoRepository = infoRepository;
        this.riskLevelRepository = riskLevelRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> preparePredictionData(Long userId) {
        List<Answers> answersList = answersRepository.findTop7ByUserIdOrderByDateDesc(userId);
        if (answersList.size() < 7) {
            throw new RuntimeException("Недостаточно ответов");
        }

        MainPersonalInfo info = infoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Профиль пользователя не найден"));

        double heartRiskProbability = riskLevelRepository.findByUserId(userId)
                .map(UserRiskLevel::getRiskProbability)
                .orElse(0.0);

        // Конвертация числа в строковый уровень риска
        String heartRiskLevelStr;
        if (heartRiskProbability < 0.33) {
            heartRiskLevelStr = "low";
        } else if (heartRiskProbability < 0.66) {
            heartRiskLevelStr = "medium";
        } else {
            heartRiskLevelStr = "high";
        }

        double heightInMeters = info.getHeight() / 100.0;
        double bmi = info.getWeight() / (heightInMeters * heightInMeters);

        List<Map<String, Integer>> weekData = new ArrayList<>();

        try {
            for (Answers a : answersList) {
                Map<String, String> map = objectMapper.readValue(a.getAnswer(), Map.class);

                Map<String, Integer> dayData = new HashMap<>();
                dayData.put("coffee", Integer.parseInt(map.getOrDefault("How many cups of coffee do you drink daily?", "0")));
                dayData.put("energy", Integer.parseInt(map.getOrDefault("How many energy drinks do you consume daily?", "0")));
                dayData.put("alcohol", Integer.parseInt(map.getOrDefault("How often do you consume alcohol per week?", "0")));

                weekData.add(dayData);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при разборе JSON: " + e.getMessage());
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("age", info.getAge());
        payload.put("bmi", Math.round(bmi * 100.0) / 100.0);
        payload.put("risk_level", heartRiskLevelStr);
        payload.put("week_data", weekData);

        String djangoUrl = "http://127.0.0.1:8000/api/v1/neurostimulator_risk/predict";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = objectMapper.writeValueAsString(payload);

            HttpEntity<String> request = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(djangoUrl, request, String.class);

            JsonNode node = objectMapper.readTree(response.getBody());

            double probability = 0.0;
            String recommendation = "";

            if (node.has("risk_score")) {
                probability = node.get("risk_score").asDouble();
            }
            if (node.has("recommendation")) {
                recommendation = node.get("recommendation").asText();
            }

            return Map.of(
                    "probabilityPercent", probability,
                    "recommendation", recommendation
            );

        } catch (Exception e) {
            throw new RuntimeException("Ошибка отправки на Django сервер: " + e.getMessage());
        }
    }
}
