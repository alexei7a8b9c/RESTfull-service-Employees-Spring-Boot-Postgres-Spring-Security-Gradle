package com.jetbrains.employees_all.repository;


import com.jetbrains.employees_all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  // Поиск по тексту с пагинацией
  @Query("select b from Employee b where " +
          "lower(b.fio) like lower(concat('%', :term, '%')) " +
          "or lower(b.email) like lower(concat('%', :term, '%'))" +
          "or lower(b.fioManager) like lower(concat('%', :term, '%'))" +
          "or lower(b.departments) like lower(concat('%', :term, '%'))"+
          "or lower(b.status) like lower(concat('%', :term, '%'))"+
          "or lower(b.urlPhoto) like lower(concat('%', :term, '%'))"+
          "or lower(b.aboutEmployee) like lower(concat('%', :term, '%'))")

  Page searchByTerm(@Param("term") String term, Pageable pageable);

  Page findByFioContainsIgnoreCase(String fio, Pageable pageable);
}
