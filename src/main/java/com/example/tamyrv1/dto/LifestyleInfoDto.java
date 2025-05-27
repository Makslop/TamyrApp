package com.example.tamyrv1.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LifestyleInfoDto {

    private Long lifestyleId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Smoking status must be specified")
    private Boolean smokes;

    @NotNull(message = "Alcohol consumption status must be specified")
    private Boolean drinksAlcohol;

    @NotNull(message = "Exercise status must be specified")
    private Boolean exercises;

    @NotBlank(message = "Fruit intake information cannot be empty")
    @Pattern(regexp = "^(Low|Moderate|High)$", message = "Fruit intake must be 'Low', 'Moderate', or 'High'")
    private String fruitIntake;
}
