package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.SurveyDTO;
import com.example.tamyrv1.model.Survey;
import com.example.tamyrv1.repository.SurveyRepository;
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
        Survey survey = new Survey(
                null,
                surveyDTO.getSurveyDescription(),
                surveyDTO.getQuestionsAnswersVariants(),
                surveyDTO.getIsDaily(),
                surveyDTO.getSurveyType()
        );
        survey = surveyRepository.save(survey);
        return convertToDTO(survey);
    }

    @Override
    public SurveyDTO getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
        return convertToDTO(survey);
    }

    @Override
    public List<SurveyDTO> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSurvey(Long surveyId) {
        surveyRepository.deleteById(surveyId);
    }

    @Override
    public SurveyDTO updateSurvey(Long surveyId, SurveyDTO surveyDTO) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        survey.setSurveyDescription(surveyDTO.getSurveyDescription());
        survey.setQuestionsAnswersVariants(surveyDTO.getQuestionsAnswersVariants());
        survey.setDaily(surveyDTO.getIsDaily());
        survey.setSurveyType(surveyDTO.getSurveyType());

        survey = surveyRepository.save(survey);
        return convertToDTO(survey);
    }

    private SurveyDTO convertToDTO(Survey survey) {
        return new SurveyDTO(
                survey.getSurveyId(),
                survey.getSurveyDescription(),
                survey.getQuestionsAnswersVariants(),
                survey.isDaily(),
                survey.getSurveyType()
        );
    }
}
