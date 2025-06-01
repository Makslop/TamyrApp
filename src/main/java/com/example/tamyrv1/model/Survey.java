/*package com.example.tamyrv1.model;

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
    private String questionsAnswersVariants; // JSON format

    @Column(name = "is_daily", nullable = false)
    private boolean isDaily = false;

    public Survey() {}

    public Survey(Long surveyId, String surveyDescription, String questionsAnswersVariants, boolean isDaily) {
        this.surveyId = surveyId;
        this.surveyDescription = surveyDescription;
        this.questionsAnswersVariants = questionsAnswersVariants;
        this.isDaily = isDaily;
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

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }
}
*/
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
    private String questionsAnswersVariants; // JSON format

    @Column(name = "is_daily", nullable = false)
    private boolean isDaily = false;

    @Column(name = "survey_type", nullable = false)
    private String surveyType; // "young" or "elderly"

    public Survey() {}

    public Survey(Long surveyId, String surveyDescription, String questionsAnswersVariants, boolean isDaily, String surveyType) {
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

    public boolean isDaily() {
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
