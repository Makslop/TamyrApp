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
public class YoungStatisticalPredictionService {

    private final AnswersRepository answersRepository;
    private final MainPersonalInfoRepository infoRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final List<String> expectedColumns = Arrays.asList(
            "age", "gender", "high_BP", "diabetes", "smoking", "alcohol",
            "coffee_cups", "energy_drinks", "bmi", "Kidney_Disease",
            "Liver_Disease", "Hypertension", "Lung_Disease", "Thyroid_Disease",
            "Arthritis", "Cancer", "Neurological_Disease"
    );

    // Маппинг вопросов к именам полей модели
    private static final Map<String, String> questionToFieldMap = Map.ofEntries(
            Map.entry("Do you have high blood pressure?", "high_BP"),
            Map.entry("Do you have diabetes?", "diabetes"),
            Map.entry("Do you smoke?", "smoking"),
            Map.entry("Do you consume alcohol?", "alcohol"),
            Map.entry("How many cups of coffee do you drink daily?", "coffee_cups"),
            Map.entry("How many energy drinks do you consume daily?", "energy_drinks"),
            Map.entry("Do you have kidney disease?", "Kidney_Disease"),
            Map.entry("Do you have liver disease?", "Liver_Disease"),
            Map.entry("Do you have hypertension?", "Hypertension"),
            Map.entry("Do you have any lung diseases?", "Lung_Disease"),
            Map.entry("Do you have thyroid disease?", "Thyroid_Disease"),
            Map.entry("Do you have arthritis?", "Arthritis"),
            Map.entry("Do you have cancer?", "Cancer"),
            Map.entry("Do you have a neurological disease?", "Neurological_Disease")
    );

    public YoungStatisticalPredictionService(AnswersRepository answersRepository,
                                             MainPersonalInfoRepository infoRepository) {
        this.answersRepository = answersRepository;
        this.infoRepository = infoRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> prepareYoungPrediction(Long userId) {
        List<Answers> answersList = answersRepository.findTop7ByUserIdOrderByDateDesc(userId);
        if (answersList.isEmpty()) {
            throw new RuntimeException("Ответы пользователя не найдены");
        }

        MainPersonalInfo info = infoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Профиль пользователя не найден"));

        Map<String, Object> inputData = new HashMap<>();

        try {
            // Возраст и пол
            inputData.put("age", info.getAge());
            // В твоей модели gender ожидается число, например 1 - male, 0 - female
            inputData.put("gender", info.getSex() != null && info.getSex().equalsIgnoreCase("male") ? 1 : 0);

            // Рассчитаем BMI
            double heightInMeters = info.getHeight() / 100.0;
            double bmi = info.getWeight() / (heightInMeters * heightInMeters);
            inputData.put("bmi", Math.round(bmi * 100.0) / 100.0);

            // Разбираем последний ответ
            Answers lastAnswer = answersList.get(0);
            Map<String, Object> answersMap = objectMapper.readValue(lastAnswer.getAnswer(), Map.class);

            // Маппим вопросы в поля и конвертируем ответы
            for (Map.Entry<String, Object> entry : answersMap.entrySet()) {
                String question = entry.getKey();
                Object val = entry.getValue();

                String field = questionToFieldMap.get(question);
                if (field != null && expectedColumns.contains(field)) {
                    if (val instanceof String) {
                        // Для yes/no и для числовых строк
                        String strVal = ((String) val).trim();

                        // Если ожидается числовое поле — парсим число, иначе yes/no в 1/0
                        if (field.equals("coffee_cups") || field.equals("energy_drinks") || field.equals("alcohol")) {
                            // Для вариантов с числами и "More than 5"/"More than 3"
                            if (strVal.equalsIgnoreCase("More than 5") || strVal.equalsIgnoreCase("More than 3")) {
                                inputData.put(field, field.equals("coffee_cups") ? 6 : 4); // Заменим на максимальное +1
                            } else {
                                try {
                                    inputData.put(field, Integer.parseInt(strVal));
                                } catch (NumberFormatException e) {
                                    inputData.put(field, 0);
                                }
                            }
                        } else {
                            inputData.put(field, "Yes".equalsIgnoreCase(strVal) ? 1 : 0);
                        }
                    } else if (val instanceof Number) {
                        inputData.put(field, ((Number) val).intValue());
                    } else {
                        inputData.put(field, 0);
                    }
                }
            }

            // Убедимся, что оставляем только ожидаемые колонки
            inputData.keySet().retainAll(expectedColumns);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при подготовке данных: " + e.getMessage());
        }

        String djangoUrl = "http://127.0.0.1:8000/api/v1/young_statistical_prediction/predict";

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
            throw new RuntimeException("Ошибка отправки на Django сервер: " + e.getMessage());
        }
    }
}
