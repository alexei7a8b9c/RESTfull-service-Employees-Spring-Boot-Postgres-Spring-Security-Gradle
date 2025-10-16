package com.jetbrains.employees_all.admin.repository;

import com.jetbrains.employees_all.admin.model.ManagersTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagersRepository extends JpaRepository<ManagersTable, Long> {
    // можно добавить методы поиска
}
