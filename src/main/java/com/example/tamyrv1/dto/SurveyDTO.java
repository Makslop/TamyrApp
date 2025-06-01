package com.example.tamyrv1.dto;

public class SurveyDTO {
    private Long surveyId;
    private String surveyDescription;
    private String questionsAnswersVariants;
    private boolean isDaily;
    private String surveyType; // "young", "elderly", etc.

    public SurveyDTO() {}

    public SurveyDTO(Long surveyId, String surveyDescription, String questionsAnswersVariants, boolean isDaily, String surveyType) {
        this.surveyId = surveyId;
        this.surveyDescription = surveyDescription;
        this.questionsAnswersVariants = questionsAnswersVariants;
        this.isDaily = isDaily;
        this.surveyType = surveyType;
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

    public boolean getIsDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }
}
