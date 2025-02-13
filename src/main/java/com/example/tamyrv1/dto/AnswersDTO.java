package com.example.tamyrv1.dto;

public class AnswersDTO {
    private Long answerId;
    private Long surveyId;
    private Long userId;
    private Long date;
    private String answer;

    public AnswersDTO() {}

    public AnswersDTO(Long answerId, Long surveyId, Long userId, Long date, String answer) {
        this.answerId = answerId;
        this.surveyId = surveyId;
        this.userId = userId;
        this.date = date;
        this.answer = answer;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}