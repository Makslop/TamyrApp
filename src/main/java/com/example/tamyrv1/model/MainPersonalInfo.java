package com.example.tamyrv1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "main_personal_info")
public class MainPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpi_id")
    private int id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User ID cannot be null")
    private int userId;

    @Column(name = "mpi_age")
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    @Max(value = 150, message = "Age must be less than or equal to 150")
    private int age;

    @Column(name = "mpi_sex")
    @NotBlank(message = "Sex cannot be blank")
    private String sex;

    @Column(name = "mpi_weight")
    @Min(value = 0, message = "Weight must be greater than or equal to 0")
    private int weight;

    @Column(name = "mpi_height")
    @Min(value = 0, message = "Height must be greater than or equal to 0")
    private int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}