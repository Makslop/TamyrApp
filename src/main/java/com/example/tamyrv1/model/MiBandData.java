/*package com.example.tamyrv1.model;
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
    private User user; // Связь с пользователем

    private int heartRate;  // Пульс (ЧСС)
    private int steps;  // Количество шагов
    private double distance;  // Дистанция (км)
    private int calories;  // Сожженные калории
    private int sleepQuality;  // Качество сна (0-100)
    private int batteryLevel;  // Уровень заряда (%)

    private LocalDateTime timestamp;  // Время записи данных

    public MiBandData() {}

    public MiBandData(User user, int heartRate, int steps, double distance, int calories, int sleepQuality, int batteryLevel, LocalDateTime timestamp) {
        this.user = user;
        this.heartRate = heartRate;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.sleepQuality = sleepQuality;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

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
    private User user; // Связь с пользователем

    private int heartRate;  // Пульс (ЧСС)
    private int steps;  // Количество шагов
    private int sleepHours;  // Количество часов сна
    private int caloriesBurned;  // Сожженные калории
    private int distance;  // Дистанция (в метрах)
    private int batteryLevel;  // Уровень заряда (%)

    private LocalDateTime timestamp;  // Время записи данных

    public MiBandData() {}

    public MiBandData(User user, int heartRate, int steps, int sleepHours, int caloriesBurned, int distance, int batteryLevel, LocalDateTime timestamp) {
        this.user = user;
        this.heartRate = heartRate;
        this.steps = steps;
        this.sleepHours = sleepHours;
        this.caloriesBurned = caloriesBurned;
        this.distance = distance;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

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
