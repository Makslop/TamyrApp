package com.example.tamyrv1.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainPersonalInfoDto {

    private int id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Age cannot be null")
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 120, message = "Age cannot exceed 120 years")
    private Integer age;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be 'Male' or 'Female'")
    private String sex;

    @NotNull(message = "Weight cannot be null")
    @Min(value = 1, message = "Weight must be greater than 0 kg")
    @Max(value = 500, message = "Weight cannot exceed 500 kg")
    private Integer weight;

    @NotNull(message = "Height cannot be null")
    @Min(value = 50, message = "Height must be at least 50 cm")
    @Max(value = 250, message = "Height cannot exceed 250 cm")
    private Integer height;
}
