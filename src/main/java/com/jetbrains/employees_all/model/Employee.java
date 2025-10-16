package com.jetbrains.employees_all.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;


import java.time.Instant;

@Entity
@Table(name = "first_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "fio", nullable = false, length = 200)
    private String fio;

    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Size(max = 200)
    @NotNull
    @Column(name = "fio_manager", nullable = false, length = 200)
    private String fioManager;

    @Size(max = 1000)
    @NotNull
    @Column(name = "departments", nullable = false, length = 1000)
    private String departments;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Size(max = 512)
    @NotNull
    @Column(name = "url_photo")
    private String urlPhoto;

    @Column(name = "about_employee", columnDefinition = "TEXT")
    private String aboutEmployee;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFiomanager() {
        return fioManager;
    }

    public void setFiomanager(String fiomanager) {
        this.fioManager = fiomanager;
    }

    public String getDepartments() {
        return departments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrlPhoto() { return urlPhoto; }
    public void setUrlPhoto(String urlPhoto) { this.urlPhoto = urlPhoto; }

    public String getAboutEmployee() { return aboutEmployee; }
    public void setAboutEmployee(String aboutEmployee) { this.aboutEmployee = aboutEmployee; }


}