package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.SurveyDTO;
import com.example.tamyrv1.dto.AnswersDTO;
import com.example.tamyrv1.model.Survey;
import com.example.tamyrv1.model.Answers;
import com.example.tamyrv1.repository.SurveyRepository;
import com.example.tamyrv1.repository.AnswersRepository;
import com.example.tamyrv1.service.SurveyService;
import com.example.tamyrv1.service.AnswersService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public SurveyDTO createSurvey(SurveyDTO surveyDTO) {
        Survey survey = new Survey(null, surveyDTO.getSurveyDescription(), surveyDTO.getQuestionsAnswersVariants());
        survey = surveyRepository.save(survey);
        return new SurveyDTO(survey.getSurveyId(), survey.getSurveyDescription(), survey.getQuestionsAnswersVariants());
    }

    @Override
    public SurveyDTO getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        return new SurveyDTO(survey.getSurveyId(), survey.getSurveyDescription(), survey.getQuestionsAnswersVariants());
    }

    @Override
    public List<SurveyDTO> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(s -> new SurveyDTO(s.getSurveyId(), s.getSurveyDescription(), s.getQuestionsAnswersVariants()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSurvey(Long surveyId) {
        surveyRepository.deleteById(surveyId);
    }
}