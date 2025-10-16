package com.jetbrains.employees_all.repository;

import com.jetbrains.employees_all.model.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    // Поиск по тексту с пагинацией
    @Query("select b from Roles b where " +
            "lower(b.fioManager) like lower(concat('%', :term, '%')) " +
            "or lower(b.department) like lower(concat('%', :term, '%'))" +
            "or lower(b.bio) like lower(concat('%', :term, '%'))" +
            "or lower(b.urlPhoto) like lower(concat('%', :term, '%'))")
    Page searchByTerm( @Param("term") String term, Pageable pageable);

    /*
     * http://localhost:8080/api/roles/search?q=темпр&page=0&size=8&sort=createdAt,asc
     */
    Page findByFioManagerContainingIgnoreCase(String fio_manager, Pageable pageable);

}
