package com.example.tamyrv1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MiBandDataDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Heart rate list JSON cannot be null")
    private String heartRateListJson;

    private int steps;
    private int caloriesBurned;
    private int distance;
    private int batteryLevel;

    @NotNull(message = "Timestamp cannot be null")
    private String timestamp;
}

