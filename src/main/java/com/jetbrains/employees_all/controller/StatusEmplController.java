package com.jetbrains.employees_all.controller;


import com.jetbrains.employees_all.repository.StatusEmplService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusEmplController {

    private final StatusEmplService service;

    public StatusEmplController(StatusEmplService service) {
        this.service = service;
    }

    /*
    1) Вывод всего набора с пагинацией и сортировкой.

    ПРИМЕРЫ:
    Сортировка по полю status,asc
    http://localhost:8080/api/status?page=0&size=5&sort=status,asc
    http://localhost:8080/api/status?page=0&size=15&sort=status,asc

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

http://localhost:8080/api/status/search?q=Архивный&page=0&size=5&sort=status,asc
http://localhost:8080/api/status/search?q=Архи&page=0&size=5&sort=status,asc

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
