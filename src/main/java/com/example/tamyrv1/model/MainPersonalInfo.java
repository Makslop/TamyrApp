/*package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "main_personal_info", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class MainPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpi_id")
    private Long id;

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

    public Long getId() {
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
*/
package com.example.tamyrv1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "main_personal_info", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class MainPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpi_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "mpi_name")
    private String name;

    @Column(name = "mpi_surname")
    private String surname;

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

    public MainPersonalInfo(User user, String name, String surname, int age, String sex, int weight, int height) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
