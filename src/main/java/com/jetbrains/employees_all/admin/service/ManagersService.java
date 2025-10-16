package com.jetbrains.employees_all.admin.service;


import com.jetbrains.employees_all.admin.model.ManagersTable;
import com.jetbrains.employees_all.admin.repository.ManagersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagersService {


    private final ManagersRepository repository;

    public ManagersService(ManagersRepository repository) {
        this.repository = repository;
    }

    public List findAll() {
        return repository.findAll();
    }

    public Optional findById( Long id) {
        return repository.findById(id);
    }

    public ManagersTable save( ManagersTable managersTable) {
        // пример простого валидирования
        if (managersTable.getFioManager() == null || managersTable.getDepartment() == null ||
                managersTable.getUrlPhoto() == null || managersTable.getBio() == null) {
            throw new IllegalArgumentException("Поля обязательны!!!");
        }
        return repository.save(managersTable);
    }

    public ManagersTable update(Long id, ManagersTable updated) {
        return repository.findById(id).map(existing -> {
            existing.setFioManager(updated.getFioManager());
            existing.setDepartment(updated.getDepartment());
            existing.setUrlPhoto(updated.getUrlPhoto());
            existing.setBio(updated.getBio());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Запись не найдена!!!"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
