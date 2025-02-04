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
    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer userId;

    @NotNull(message = "Age cannot be null")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;

    @NotBlank(message = "Sex cannot be blank")
    @Pattern(regexp = "MALE|FEMALE", message = "Sex must be either MALE or FEMALE")
    private String sex;

    @NotNull(message = "Weight cannot be null")
    @Min(value = 1, message = "Weight must be greater than 0")
    private Integer weight;

    @NotNull(message = "Height cannot be null")
    @Min(value = 1, message = "Height must be greater than 0")
    private Integer height;
}
