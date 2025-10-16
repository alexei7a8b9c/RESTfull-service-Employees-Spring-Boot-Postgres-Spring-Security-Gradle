package com.jetbrains.employees_all.repository;

import com.jetbrains.employees_all.model.StatusEmpl;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusEmplRepository extends JpaRepository<StatusEmpl, Long> {

    // Поиск по тексту с пагинацией
    @Query("select b from StatusEmpl b where " +
            "lower(b.status) like lower(concat('%', :term, '%')) ")
    Page searchByTerm( @Param("term") String term, Pageable pageable);

    /*
     * http://localhost:8080/api/status/search?q=темпр&page=0&size=8&sort=createdAt,asc
     */
    Page findByStatusContainsIgnoreCase(String status, Pageable pageable);

    String status( @Size(max = 200) @NotNull String status );
}
