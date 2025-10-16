package com.jetbrains.employees_all.admin.repository;

import com.jetbrains.employees_all.admin.model.StatusEmployeeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEmployeeRepository extends JpaRepository<StatusEmployeeTable, Long> {
    // можно добавить методы поиска
}
