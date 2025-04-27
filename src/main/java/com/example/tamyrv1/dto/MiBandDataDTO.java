package com.example.tamyrv1.dto;

import java.time.LocalDateTime;
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

    private int heartRate;
    private int steps;
    private int caloriesBurned;
    private int distance;
    private int batteryLevel;
    private LocalDateTime timestamp;
}
