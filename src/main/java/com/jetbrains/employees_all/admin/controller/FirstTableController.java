package com.jetbrains.employees_all.admin.controller;

import com.jetbrains.employees_all.admin.model.FirstTable;
import com.jetbrains.employees_all.admin.service.FirstTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/firsttable")

/*
В этом контроллере происходят операции с таблицей first_table
GET POST PUT DELETE
 */

public class FirstTableController {

    private final FirstTableService service;

    public FirstTableController(FirstTableService service) {
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
    public FirstTable create(@RequestBody FirstTable firstTable) {
        return service.save(firstTable);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody FirstTable updated) {
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