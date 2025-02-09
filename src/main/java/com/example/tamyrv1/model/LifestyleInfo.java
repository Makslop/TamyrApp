/*package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lifestyle_info")
public class LifestyleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifestyle_id")
    private Integer lifestyleId;

    @ManyToOne
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "smokes", nullable = false)
    private boolean smokes;

    @Column(name = "drinks_alcohol", nullable = false)
    private boolean drinksAlcohol;

    @Column(name = "exercises", nullable = false)
    private boolean exercises;

    @Column(name = "fruit_intake", nullable = false)
    private String fruitIntake;

    public LifestyleInfo() {
    }

    public LifestyleInfo(Integer lifestyleId, Integer userId, boolean smokes, boolean drinksAlcohol, boolean exercises, String fruitIntake) {
        this.lifestyleId = lifestyleId;
        this.userId = userId;
        this.smokes = smokes;
        this.drinksAlcohol = drinksAlcohol;
        this.exercises = exercises;
        this.fruitIntake = fruitIntake;
    }

    public Integer getLifestyleId() {
        return lifestyleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isSmokes() {
        return smokes;
    }

    public boolean isDrinksAlcohol() {
        return drinksAlcohol;
    }

    public boolean isExercises() {
        return exercises;
    }

    public String getFruitIntake() {
        return fruitIntake;
    }

    // 🔹 Сеттеры
    public void setLifestyleId(Integer lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSmokes(boolean smokes) {
        this.smokes = smokes;
    }

    public void setDrinksAlcohol(boolean drinksAlcohol) {
        this.drinksAlcohol = drinksAlcohol;
    }

    public void setExercises(boolean exercises) {
        this.exercises = exercises;
    }

    public void setFruitIntake(String fruitIntake) {
        this.fruitIntake = fruitIntake;
    }
}
*/
/*
package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lifestyle_info")
public class LifestyleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifestyle_id")
    private Integer lifestyleId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "smokes", nullable = false)
    private boolean smokes;

    @Column(name = "drinks_alcohol", nullable = false)
    private boolean drinksAlcohol;

    @Column(name = "exercises", nullable = false)
    private boolean exercises;

    @Column(name = "fruit_intake", nullable = false)
    private String fruitIntake;

    public LifestyleInfo() {
    }

    public LifestyleInfo(User user, boolean smokes, boolean drinksAlcohol, boolean exercises, String fruitIntake) {
        this.user = user;
        this.smokes = smokes;
        this.drinksAlcohol = drinksAlcohol;
        this.exercises = exercises;
        this.fruitIntake = fruitIntake;
    }

    public Integer getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(Integer lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
*/
package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lifestyle_info", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class LifestyleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifestyle_id")
    private Integer lifestyleId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "smokes", nullable = false)
    private boolean smokes;

    @Column(name = "drinks_alcohol", nullable = false)
    private boolean drinksAlcohol;

    @Column(name = "exercises", nullable = false)
    private boolean exercises;

    @Column(name = "fruit_intake", nullable = false)
    private String fruitIntake;

    public LifestyleInfo() {}

    public LifestyleInfo(User user, boolean smokes, boolean drinksAlcohol, boolean exercises, String fruitIntake) {
        this.user = user;
        this.smokes = smokes;
        this.drinksAlcohol = drinksAlcohol;
        this.exercises = exercises;
        this.fruitIntake = fruitIntake;
    }

    public Integer getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(Integer lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
