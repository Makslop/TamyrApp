package com.example.tamyrv1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LifestyleInfoDto {
    private Integer lifestyleId;
    private Integer userId;
    private boolean smokes;
    private boolean drinksAlcohol;
    private boolean exercises;
    private String fruitIntake;
}