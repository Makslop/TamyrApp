package com.example.tamyrv1.service;

import com.example.tamyrv1.model.Answers;
import com.example.tamyrv1.model.MainPersonalInfo;
import com.example.tamyrv1.repository.AnswersRepository;
import com.example.tamyrv1.repository.MainPersonalInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ElderlyStatisticalPredictionService {

    private final AnswersRepository answersRepository;
    private final MainPersonalInfoRepository infoRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final List<String> expectedColumns = Arrays.asList(
            "health_score", "med_visits", "age", "male", "female", "copd",
            "rheumatic", "ulcers", "liver_disease", "diabetes", "cancer",
            "hypertension", "infectious_dis", "depression", "drug_use",
            "clean_room", "yard_work", "water_wood", "laundry_sew", "cook",
            "clean_house", "shopping", "still_working", "community_work",
            "social_active", "reading_tv", "other_hobbies"
    );

    private static final Map<String, String> questionToFieldMap = Map.ofEntries(
            Map.entry("Do you clean your room?", "clean_room"),
            Map.entry("Do you work in the yard or park?", "yard_work"),
            Map.entry("Do you fetch water or wood?", "water_wood"),
            Map.entry("Do you do laundry or sewing?", "laundry_sew"),
            Map.entry("Do you cook food?", "cook"),
            Map.entry("Do you do house cleaning or repairs?", "clean_house"),
            Map.entry("Do you buy medicine or groceries yourself?", "shopping"),
            Map.entry("Are you still working?", "still_working"),
            Map.entry("Do you participate in community work?", "community_work"),
            Map.entry("Do you interact with relatives or neighbors?", "social_active"),
            Map.entry("Do you read newspapers/magazines or watch TV?", "reading_tv"),
            Map.entry("Do you have various hobbies?", "other_hobbies"),
            Map.entry("Did you avoid medical help due to good condition?", "health_score"), // пример
            Map.entry("Did you seek help only for acute illnesses?", "med_visits"), // пример
            Map.entry("Did you have chronic heart failure?", "copd"),
            Map.entry("Do you have COPD?", "copd"),
            Map.entry("Do you have rheumatic or autoimmune diseases?", "rheumatic"),
            Map.entry("Do you have stomach or duodenal ulcers?", "ulcers"),
            Map.entry("Do you have liver disease?", "liver_disease"),
            Map.entry("Do you have diabetes?", "diabetes"),
            Map.entry("Do you have cancer?", "cancer"),
            Map.entry("Do you have hypertension?", "hypertension"),
            Map.entry("Do you have infectious or parasitic diseases?", "infectious_dis"),
            Map.entry("Do you have depression?", "depression"),
            Map.entry("Do you have psychosis?", "depression"), // если есть другое поле - поправь
            Map.entry("Do you have drug or addiction disorders?", "drug_use"),
            Map.entry("Do you have other conditions?", "other_hobbies") // пример
    );

    public ElderlyStatisticalPredictionService(AnswersRepository answersRepository,
                                               MainPersonalInfoRepository infoRepository) {
        this.answersRepository = answersRepository;
        this.infoRepository = infoRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> prepareElderlyPrediction(Long userId) {
        List<Answers> answersList = answersRepository.findTop7ByUserIdOrderByDateDesc(userId);
        if (answersList.isEmpty()) {
            throw new RuntimeException("Ответы пользователя не найдены");
        }

        MainPersonalInfo info = infoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Профиль пользователя не найден"));

        Map<String, Object> inputData = new HashMap<>();

        try {
            System.out.println("User profile: age=" + info.getAge() + ", sex=" + info.getSex());

            inputData.put("age", info.getAge());
            inputData.put("male", info.getSex() != null && info.getSex().equalsIgnoreCase("male") ? 1 : 0);
            inputData.put("female", info.getSex() != null && info.getSex().equalsIgnoreCase("female") ? 1 : 0);

            inputData.put("health_score", 80); // Заглушка, замени на реальные данные
            inputData.put("med_visits", 2);    // Заглушка, замени на реальные данные

            Answers lastAnswer = answersList.get(0);
            System.out.println("Raw last answer JSON: " + lastAnswer.getAnswer());

            Map<String, Object> answersMap = objectMapper.readValue(lastAnswer.getAnswer(), Map.class);
            System.out.println("Parsed answersMap: " + answersMap);

            for (Map.Entry<String, Object> entry : answersMap.entrySet()) {
                String question = entry.getKey();
                Object val = entry.getValue();
                System.out.println("Question: '" + question + "', answer: '" + val + "'");

                String field = questionToFieldMap.get(question);
                if (field != null && expectedColumns.contains(field)) {
                    if (val instanceof String) {
                        inputData.put(field, "Yes".equalsIgnoreCase((String) val) ? 1 : 0);
                    } else if (val instanceof Number) {
                        inputData.put(field, ((Number) val).intValue());
                    } else {
                        inputData.put(field, 0);
                    }
                }
            }

            inputData.keySet().retainAll(expectedColumns);

            System.out.println("Final inputData prepared for model: " + inputData);

        } catch (Exception e) {
            System.out.println("❌ Ошибка при подготовке данных: " + e.getMessage());
            throw new RuntimeException("Ошибка при подготовке данных: " + e.getMessage());
        }

        String djangoUrl = "http://127.0.0.1:8000/api/v1/old_statistical_prediction_v1/predict";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = objectMapper.writeValueAsString(inputData);
            System.out.println("=== Отправка на Django ===");
            System.out.println("URL: " + djangoUrl);
            System.out.println("Request JSON: " + json);

            HttpEntity<String> request = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(djangoUrl, request, String.class);

            System.out.println("=== Ответ от Django ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());

            return Map.of(
                    "sent_data", inputData,
                    "django_response", response.getBody()
            );

        } catch (Exception e) {
            System.out.println("❌ Ошибка отправки на Django сервер: " + e.getMessage());
            throw new RuntimeException("Ошибка отправки на Django сервер: " + e.getMessage());
        }
    }
}
