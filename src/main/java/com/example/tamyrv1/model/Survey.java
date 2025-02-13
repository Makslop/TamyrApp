package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "survey_description", columnDefinition = "TEXT", nullable = false)
    private String surveyDescription;

    @Column(name = "questions_answers_variants", columnDefinition = "TEXT", nullable = false)
    private String questionsAnswersVariants; // JSON Format

    public Survey() {}

    public Survey(Long surveyId, String surveyDescription, String questionsAnswersVariants) {
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