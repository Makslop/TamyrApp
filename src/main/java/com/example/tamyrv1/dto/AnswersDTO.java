package com.example.tamyrv1.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AnswersDTO {
    private Long answerId;
    private Long surveyId;
    private Long userId;
    private Timestamp date;

    private String answer;

    public AnswersDTO() {}

    public AnswersDTO(Long answerId, Long surveyId, Long userId, String answer) {
        this.answerId = answerId;
        this.surveyId = surveyId;
        this.userId = userId;
        this.answer = answer;
    }
    public AnswersDTO(Long answerId, Long surveyId, Long userId,Timestamp date, String answer) {
        this.answerId = answerId;
        this.surveyId = surveyId;
        this.date = date;
        this.userId = userId;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}