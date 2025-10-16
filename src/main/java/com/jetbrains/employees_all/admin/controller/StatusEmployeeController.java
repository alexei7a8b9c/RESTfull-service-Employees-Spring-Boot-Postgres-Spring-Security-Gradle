package com.jetbrains.employees_all.admin.controller;

import com.jetbrains.employees_all.admin.model.StatusEmployeeTable;
import com.jetbrains.employees_all.admin.service.StatusEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status_employee")

/*
В этом контроллере происходят операции с таблицей status_employee
GET POST PUT DELETE
 */

public class StatusEmployeeController {


    private final StatusEmployeeService service;

    public StatusEmployeeController(StatusEmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StatusEmployeeTable create( @RequestBody StatusEmployeeTable statusEmployeeTable) {
        return service.save(statusEmployeeTable);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody StatusEmployeeTable updated) {
        try {
            return ResponseEntity.ok(service.update(id, updated));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
