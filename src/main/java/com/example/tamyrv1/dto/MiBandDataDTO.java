/*package com.example.tamyrv1.dto;

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
    private String heartRateListJson;  // JSON-массив строкой, например "[72,75,70,68,...]"

    private int steps;
    private int caloriesBurned;
    private int distance;
    private int batteryLevel;

    @NotNull(message = "Timestamp cannot be null")
    private String timestamp;  // теперь строка, например "2025-06-03T21:14:00"
}
*/
package com.example.tamyrv1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MiBandDataDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Heart rate list JSON cannot be null")
    private String heartRateListJson;  // JSON-массив строкой, например "[72,75,70,68,...]"

    private int steps;
    private int caloriesBurned;
    private int distance;
    private int batteryLevel;
    private LocalDateTime timestamp;
}
