package com.example.tamyrv1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mi_band_data")
public class MiBandData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int heartRate;
    private int steps;
    private int caloriesBurned;
    private int distance;  // в метрах
    private int batteryLevel;
    private LocalDateTime timestamp;

    public MiBandData() {}

    public MiBandData(User user, int heartRate, int steps, int caloriesBurned, int distance, int batteryLevel, LocalDateTime timestamp) {
        this.user = user;
        this.heartRate = heartRate;
        this.steps = steps;
        this.caloriesBurned = caloriesBurned;
        this.distance = distance;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    // --- Геттеры и сеттеры ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
