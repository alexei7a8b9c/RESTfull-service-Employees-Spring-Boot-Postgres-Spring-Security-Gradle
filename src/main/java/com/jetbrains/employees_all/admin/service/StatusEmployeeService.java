package com.jetbrains.employees_all.admin.service;

import com.jetbrains.employees_all.admin.model.StatusEmployeeTable;
import com.jetbrains.employees_all.admin.repository.StatusEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusEmployeeService {

    private final StatusEmployeeRepository repository;

    public StatusEmployeeService(StatusEmployeeRepository repository) {
        this.repository = repository;
    }

    public List findAll() {
        return repository.findAll();
    }

    public Optional findById( Long id) {
        return repository.findById(id);
    }

    public StatusEmployeeTable save( StatusEmployeeTable statusEmployeeTable) {
        // пример простого валидирования
        if (statusEmployeeTable.getStatus() == null) {
            throw new IllegalArgumentException("Поле обязательно!!!");
        }
        return repository.save(statusEmployeeTable);
    }

    public StatusEmployeeTable update(Long id, StatusEmployeeTable updated) {
        return repository.findById(id).map(existing -> {
            existing.setStatus(updated.getStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Запись не найдена!!!"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
