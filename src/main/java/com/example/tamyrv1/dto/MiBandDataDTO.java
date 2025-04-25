/*package com.example.tamyrv1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MiBandDataDTO {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    private int heartRate;
    private int steps;
    private double distance;
    private int calories;
    private int sleepQuality;
    private int batteryLevel;
    private LocalDateTime timestamp;

    // Конструкторы
    public MiBandDataDTO() {}

    public MiBandDataDTO(Long userId, int heartRate, int steps, double distance, int calories, int sleepQuality, int batteryLevel, LocalDateTime timestamp) {
        this.userId = userId;
        this.heartRate = heartRate;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.sleepQuality = sleepQuality;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public int getSleepQuality() { return sleepQuality; }
    public void setSleepQuality(int sleepQuality) { this.sleepQuality = sleepQuality; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) { this.batteryLevel = batteryLevel; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
*/
package com.example.tamyrv1.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public class MiBandDataDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    private int heartRate;
    private int steps;
    private int sleepHours;
    private int caloriesBurned;
    private int distance;
    private int batteryLevel;
    private LocalDateTime timestamp;

    // Конструктор без параметров
    public MiBandDataDTO() {}

    // Конструктор с параметрами
    public MiBandDataDTO(Long userId, int heartRate, int steps, int sleepHours, int caloriesBurned, int distance, int batteryLevel, LocalDateTime timestamp) {
        this.userId = userId;
        this.heartRate = heartRate;
        this.steps = steps;
        this.sleepHours = sleepHours;
        this.caloriesBurned = caloriesBurned;
        this.distance = distance;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }

    public int getSleepHours() { return sleepHours; }
    public void setSleepHours(int sleepHours) { this.sleepHours = sleepHours; }

    public int getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) { this.batteryLevel = batteryLevel; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
