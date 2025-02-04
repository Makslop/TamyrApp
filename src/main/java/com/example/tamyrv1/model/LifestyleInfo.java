package com.example.tamyrv1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lifestyle_info")
public class LifestyleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifestyle_id")
    private Integer lifestyleId;

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
}