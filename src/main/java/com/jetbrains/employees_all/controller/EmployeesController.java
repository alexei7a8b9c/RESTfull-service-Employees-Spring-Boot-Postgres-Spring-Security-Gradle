package com.jetbrains.employees_all.controller;


import com.jetbrains.employees_all.repository.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private final EmployeeService service;

    public EmployeesController(EmployeeService service) {
        this.service = service;
    }

    /*
    1) Вывод всего набора с пагинацией и сортировкой.
       Поля:
    fio, email, fioManager, departments, status, urlPhoto,
    aboutEmployee, fioManager(большая буква).

    page=0 Первая страница пагинации
    size=5 Количество записей на странице
    sort=fio,asc Сортировка по полю fio.
    asc/desc Сортировка по возрастанию/убыванию

    ПРИМЕРЫ:
    Сортировка по полю fio,asc
    localhost:8080/api/employees?page=0&size=5&sort=fio,asc

    sort=fioManager,asc Сортировка по полю fioManager, asc
    localhost:8080/api/employees?page=0&size=8&sort=fioManager,asc
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
    Пример:
    Вместо слова "терм" вписываем искомое значение любого из полей.
    Поля:
    fio, email, fioManager, departments, status, urlPhoto, aboutEmployee

    GET  localhost:8080/api/employees/search?q=терм&page=0&size=5&sort=createdAt,desc
    GET localhost:8080/api/employees/search?q=Егор&page=0&size=8&sort=createdAt,asc

    Пример: Поиск отдела Marketing.
    page=0 Первая страница пагинации
    size=5 Количество записей на странице
    desc Сортировка по убыванию

    http://localhost:8080/api/employees/search?q=Marketing&page=0&size=5&sort=createdAt,desc
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