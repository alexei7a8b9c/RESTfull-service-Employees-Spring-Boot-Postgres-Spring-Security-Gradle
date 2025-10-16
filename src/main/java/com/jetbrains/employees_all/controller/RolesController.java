package com.jetbrains.employees_all.controller;

import com.jetbrains.employees_all.repository.RolesService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService service;

    public RolesController(RolesService service) {
        this.service = service;
    }

    /*
    1) Вывод всего набора с пагинацией и сортировкой.

    page=0 Первая страница пагинации
    size=5 Количество записей на странице
    sort=fio,asc Сортировка по полю fio.
    asc/desc Сортировка по возрастанию/убыванию

    ПРИМЕРЫ:
    Сортировка по полю fio,asc
    http://localhost:8080/api/roles?page=0&size=5&sort=department,asc
    http://localhost:8080/api/roles?page=0&size=5&sort=fioManager,asc
    http://localhost:8080/api/roles?page=0&size=5&sort=bio,asc
    http://localhost:8080/api/roles?page=0&size=5&sort=urlPhoto,asc
    */

    @GetMapping
    public Page getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        return service.getAll(page, size, sort);
    }


    /*
    2) Поиск по терму с пагинацией и сортировкой
    Пример: Поиск отдела Marketing.
    page=0 Первая страница пагинации
    size=5 Количество записей на странице
    desc Сортировка по убыванию
http://localhost:8080/api/roles/search?q=Mark&page=0&size=5&sort=department,asc

http://localhost:8080/api/roles/search?q=Иван&page=0&size=5&sort=fioManager,asc

 */

    @GetMapping("/search")
    public Page search(
            @RequestParam(value = "q") String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        return service.search(query, page, size, sort);
    }

}
