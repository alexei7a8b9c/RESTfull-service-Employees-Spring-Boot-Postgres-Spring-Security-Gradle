package com.jetbrains.employees_all.admin.repository;


import com.jetbrains.employees_all.admin.model.FirstTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstTableRepository extends JpaRepository<FirstTable, Long> {
    // можно добавить методы поиска по полям, например:
    boolean existsByEmail(String email);
}
