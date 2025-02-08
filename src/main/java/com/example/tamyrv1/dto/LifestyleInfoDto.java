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

    private Integer lifestyleId;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive number")
    private Integer userId;

    private boolean smokes;
    private boolean drinksAlcohol;
    private boolean exercises;

    @NotBlank(message = "Fruit intake description cannot be blank")
    @Size(min = 3, max = 100, message = "Fruit intake description must be between 3 and 100 characters")
    private String fruitIntake;
}
