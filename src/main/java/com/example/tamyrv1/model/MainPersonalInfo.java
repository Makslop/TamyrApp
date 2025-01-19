package com.example.tamyrv1.model;
import jakarta.persistence.*;

@Entity
@Table(name = "main_personal_info")
public class MainPersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mpiId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "mpi_age", nullable = false)
    private Integer age;

    @Column(name = "mpi_sex", nullable = false)
    private String sex;

    @Column(name = "mpi_dob", nullable = false)
    private java.sql.Date dateOfBirth;

    @Column(name = "mpi_weight", nullable = false)
    private Integer weight;

    @Column(name = "mpi_height", nullable = false)
    private Integer height;

    public Long getMpiId() {
        return mpiId;
    }

    public void setMpiId(Long mpiId) {
        this.mpiId = mpiId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
