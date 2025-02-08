package com.example.tamyrv1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "lifestyle_info")
public class LifestyleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifestyle_id")
    private Integer lifestyleId;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive integer")
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "smokes", nullable = false)
    private boolean smokes;

    @Column(name = "drinks_alcohol", nullable = false)
    private boolean drinksAlcohol;

    @Column(name = "exercises", nullable = false)
    private boolean exercises;

    @NotBlank(message = "Fruit intake cannot be empty")
    @Pattern(regexp = "^(low|medium|high)$", message = "Fruit intake must be 'low', 'medium', or 'high'")
    @Column(name = "fruit_intake", nullable = false)
    private String fruitIntake;

    // Пустой конструктор
    public LifestyleInfo() {}

    // Конструктор со всеми параметрами
    public LifestyleInfo(Integer lifestyleId, Integer userId, boolean smokes, boolean drinksAlcohol, boolean exercises, String fruitIntake) {
        this.lifestyleId = lifestyleId;
        this.userId = userId;
        this.smokes = smokes;
        this.drinksAlcohol = drinksAlcohol;
        this.exercises = exercises;
        this.fruitIntake = fruitIntake;
    }

    // Геттеры и сеттеры
    public Integer getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(Integer lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isSmokes() {
        return smokes;
    }

    public void setSmokes(boolean smokes) {
        this.smokes = smokes;
    }

    public boolean isDrinksAlcohol() {
        return drinksAlcohol;
    }

    public void setDrinksAlcohol(boolean drinksAlcohol) {
        this.drinksAlcohol = drinksAlcohol;
    }

    public boolean isExercises() {
        return exercises;
    }

    public void setExercises(boolean exercises) {
        this.exercises = exercises;
    }

    public String getFruitIntake() {
        return fruitIntake;
    }

    public void setFruitIntake(String fruitIntake) {
        this.fruitIntake = fruitIntake;
    }
}
