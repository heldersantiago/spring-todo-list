package com.nexus.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BaseService<T> {
    Page<T> getAll(Pageable pageable);

    Iterable<T> getAll(Sort sort);

    T getById(Long id);

    T create(T t);

    T update(Long Id, T t);

    void delete(Long id);
}
