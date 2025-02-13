package com.example.tamyrv1.controller;

import com.example.tamyrv1.dto.SurveyDTO;
import com.example.tamyrv1.dto.AnswersDTO;
import com.example.tamyrv1.service.SurveyService;
import com.example.tamyrv1.service.AnswersService;
import com.example.tamyrv1.service.UserService;
import com.example.tamyrv1.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyService surveyService;
    private final AnswersService answersService;
    private final UserServiceImpl userServiceImpl;

    public SurveyController(SurveyService surveyService, AnswersService answersService, UserServiceImpl userServiceImpl) {
        this.surveyService = surveyService;
        this.answersService = answersService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<SurveyDTO> createSurvey(@RequestBody SurveyDTO surveyDTO) {
        return ResponseEntity.ok(surveyService.createSurvey(surveyDTO));
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyDTO> getSurveyById(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.getSurveyById(surveyId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SurveyDTO>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.getAllSurveys());
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswersDTO answersDTO) {
        if (!userServiceImpl.existsById(answersDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User with ID " + answersDTO.getUserId() + " does not exist.");
        }
        return ResponseEntity.ok(answersService.submitAnswer(answersDTO));
    }

    @GetMapping("/{surveyId}/answers")
    public ResponseEntity<List<AnswersDTO>> getAnswersBySurveyId(@PathVariable Long surveyId) {
        return ResponseEntity.ok(answersService.getAnswersBySurveyId(surveyId));
    }

    @GetMapping("/user/{userId}/answers")
    public ResponseEntity<List<AnswersDTO>> getAnswersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(answersService.getAnswersByUserId(userId));
    }
}