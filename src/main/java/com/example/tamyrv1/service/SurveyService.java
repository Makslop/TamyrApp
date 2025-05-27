package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.SurveyDTO;
import com.example.tamyrv1.dto.AnswersDTO;
import java.util.List;

public interface SurveyService {
    SurveyDTO createSurvey(SurveyDTO surveyDTO);
    SurveyDTO getSurveyById(Long surveyId);
    List<SurveyDTO> getAllSurveys();
    void deleteSurvey(Long surveyId);
    SurveyDTO updateSurvey(Long surveyId, SurveyDTO surveyDTO);
}