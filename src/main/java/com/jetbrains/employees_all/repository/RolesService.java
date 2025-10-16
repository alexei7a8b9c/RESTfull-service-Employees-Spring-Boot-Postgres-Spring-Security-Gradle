package com.jetbrains.employees_all.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private final RolesRepository repository;

    public RolesService( RolesRepository repository) {
        this.repository = repository;
    }

    // Вспомогательный метод для парной сортировки
    private Pageable createPageable( int page, int size, String sortParam) {
        if (sortParam == null || sortParam.isEmpty()) {
            return PageRequest.of(page, size);
        }
        // ожидаем формат "field,dir"
        String[] parts = sortParam.split(",");
        String field = parts[0];
        Sort.Direction direction = (parts.length > 1 && parts[1].equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(direction, field));
    }

    // Вывод всей коллекции с пагинацией + сортировка
    public Page getAll( int page, int size, String sort) {
        Pageable pageable = createPageable(page, size, sort);
        return repository.findAll(pageable);
    }

    // Поиск по терму с пагинацией + сортировка
    public Page search(String term, int page, int size, String sort) {
        Pageable pageable = createPageable(page, size, sort);
        return repository.searchByTerm(term, pageable);
    }

    // Поиск по fio_manager (опционально)
    public Page searchByFioManager( String fio_manager, int page, int size, String sort) {
        Pageable pageable = createPageable(page, size, sort);
        return repository.findByFioManagerContainingIgnoreCase(fio_manager, pageable);
    }
}
