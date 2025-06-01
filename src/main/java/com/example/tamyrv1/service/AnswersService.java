package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.AnswersDTO;

import java.util.List;

public interface AnswersService {
    AnswersDTO submitAnswer(AnswersDTO answersDTO);
    List<AnswersDTO> getAnswersBySurveyId(Long surveyId);
    List<AnswersDTO> getAnswersByUserId(Long userId);
    boolean hasUserAnsweredSurvey(Long userId, Long surveyId);
}
