package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_risk_level")
public class UserRiskLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "risk_probability", nullable = false)
    private Double riskProbability;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;  // "низкий", "средний", "высокий"

    public UserRiskLevel() {}

    public UserRiskLevel(Long userId, Double riskProbability, String riskLevel) {
        this.userId = userId;
        this.riskProbability = riskProbability;
        this.riskLevel = riskLevel;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getRiskProbability() {
        return riskProbability;
    }

    public void setRiskProbability(Double riskProbability) {
        this.riskProbability = riskProbability;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
