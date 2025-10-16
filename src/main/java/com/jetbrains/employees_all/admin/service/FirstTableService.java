package com.jetbrains.employees_all.admin.service;

import com.jetbrains.employees_all.admin.model.FirstTable;
import com.jetbrains.employees_all.admin.repository.FirstTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FirstTableService {

    private final FirstTableRepository repository;

    public FirstTableService(FirstTableRepository repository) {
        this.repository = repository;
    }

    public List findAll() {
        return repository.findAll();
    }

    public Optional findById(Long id) {
        return repository.findById(id);
    }

    public FirstTable save(FirstTable firstTable) {
        // пример простого валидирования
        if (firstTable.getEmail() == null || firstTable.getFio() == null ||
                firstTable.getFioManager() == null || firstTable.getDepartments() == null ||
                firstTable.getStatus() == null || firstTable.getUrlPhoto() == null ||
                firstTable.getAboutEmployee() == null) {
            throw new IllegalArgumentException("Поля обязательны!!!");
        }
        return repository.save(firstTable);
    }

    public FirstTable update(Long id, FirstTable updated) {
        return repository.findById(id).map(existing -> {
            existing.setFio(updated.getFio());
            existing.setEmail(updated.getEmail());
            existing.setFioManager(updated.getFioManager());
            existing.setDepartments(updated.getDepartments());
            existing.setStatus(updated.getStatus());
            existing.setUrlPhoto(updated.getUrlPhoto());
            existing.setAboutEmployee(updated.getAboutEmployee());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Запись не найдена!!!"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}