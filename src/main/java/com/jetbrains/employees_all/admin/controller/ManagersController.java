package com.jetbrains.employees_all.admin.controller;


import com.jetbrains.employees_all.admin.model.ManagersTable;
import com.jetbrains.employees_all.admin.service.ManagersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagersController {
/*
В этом контроллере происходят операции с таблицей managers
GET POST PUT DELETE
* */

    private final ManagersService service;

    public ManagersController(ManagersService service) {
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
    public ManagersTable create( @RequestBody ManagersTable managersTable) {
        return service.save(managersTable);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ManagersTable updated) {
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
