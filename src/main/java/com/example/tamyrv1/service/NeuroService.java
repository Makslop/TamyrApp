package com.example.tamyrv1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NeuroService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String djangoUrl = "http://127.0.0.1:8000/api/v1/neurostimulator_risk/predict";
    private final ObjectMapper objectMapper = new ObjectMapper(); // 👈 Jackson

    public ResponseEntity<?> forwardToDjango(Map<String, Object> requestData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            // ✅ Сериализуем в валидный JSON
            String json = objectMapper.writeValueAsString(requestData);

            // 🖨 Лог запроса
            System.out.println("=== Отправка на Django ===");
            System.out.println("URL: " + djangoUrl);
            System.out.println("Request JSON: " + json);

            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(djangoUrl, entity, String.class);

            // 🖨 Лог ответа
            System.out.println("=== Ответ от Django ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());

            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

        } catch (Exception e) {
            System.out.println("❌ Ошибка при запросе к Django: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при обращении к Django API: " + e.getMessage());
        }
    }
}
