package com.jetbrains.employees_all.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "first_table")
public class FirstTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(nullable = false)
    private String fio;

    @Size(max = 200)
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 200)
    @NotNull
    @Column(name = "fio_manager")
    private String fioManager;

    @Size(max = 250)
    @NotNull
    @Column(name = "departments")
    private String departments;

    @Size(max = 50)
    @NotNull
    @Column(name = "status")
    private String status;

    @Size(max = 512)
    @NotNull
    @Column(name = "url_photo")
    private String urlPhoto;

    @Column(name = "about_employee", columnDefinition = "TEXT")
    private String aboutEmployee;

    // геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFio() { return fio; }
    public void setFio(String fio) { this.fio = fio; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFioManager() { return fioManager; }
    public void setFioManager(String fioManager) { this.fioManager = fioManager; }

    public String getDepartments() { return departments; }
    public void setDepartments(String departments) { this.departments = departments; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUrlPhoto() { return urlPhoto; }
    public void setUrlPhoto(String urlPhoto) { this.urlPhoto = urlPhoto; }

    public String getAboutEmployee() { return aboutEmployee; }
    public void setAboutEmployee(String aboutEmployee) { this.aboutEmployee = aboutEmployee; }
}