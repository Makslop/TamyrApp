package com.example.tamyrv1.model;
import jakarta.persistence.*;

@Entity
@Table(name = "lifestyle_info")
public class LifestyleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lifestyleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "smokes", nullable = false)
    private Boolean smokes;

    @Column(name = "drinks_alcohol", nullable = false)
    private Boolean drinksAlcohol;

    @Column(name = "exercises", nullable = false)
    private Boolean exercises;

    @Column(name = "fruit_intake", nullable = false)
    private String fruitIntake;

    public Long getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(Long lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getSmokes() {
        return smokes;
    }

    public void setSmokes(Boolean smokes) {
        this.smokes = smokes;
    }

    public Boolean getDrinksAlcohol() {
        return drinksAlcohol;
    }

    public void setDrinksAlcohol(Boolean drinksAlcohol) {
        this.drinksAlcohol = drinksAlcohol;
    }

    public Boolean getExercises() {
        return exercises;
    }

    public void setExercises(Boolean exercises) {
        this.exercises = exercises;
    }

    public String getFruitIntake() {
        return fruitIntake;
    }

    public void setFruitIntake(String fruitIntake) {
        this.fruitIntake = fruitIntake;
    }
}
