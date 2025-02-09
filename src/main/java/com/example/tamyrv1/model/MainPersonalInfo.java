package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "main_personal_info", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class MainPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpi_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "mpi_age")
    private int age;

    @Column(name = "mpi_sex")
    private String sex;

    @Column(name = "mpi_weight")
    private int weight;

    @Column(name = "mpi_height")
    private int height;

    public MainPersonalInfo() {
    }

    public MainPersonalInfo(User user, int age, String sex, int weight, int height) {
        this.user = user;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
