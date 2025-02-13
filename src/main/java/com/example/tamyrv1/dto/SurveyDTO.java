package com.example.tamyrv1.dto;

public class SurveyDTO {
    private Long surveyId;
    private String surveyDescription;
    private String questionsAnswersVariants;

    public SurveyDTO() {}

    public SurveyDTO(Long surveyId, String surveyDescription, String questionsAnswersVariants) {
        this.surveyId = surveyId;
        this.surveyDescription = surveyDescription;
        this.questionsAnswersVariants = questionsAnswersVariants;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public String getQuestionsAnswersVariants() {
        return questionsAnswersVariants;
    }

    public void setQuestionsAnswersVariants(String questionsAnswersVariants) {
        this.questionsAnswersVariants = questionsAnswersVariants;
    }
}