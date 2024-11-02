package com.nexus.services;

import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;
import com.nexus.interfaces.CategoryService;
import com.nexus.mappers.CategoryMapper;
import com.nexus.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category update(Long Id, Category category) {
        return null;
    }

    @Override
    public Iterable<Category> getAll(Sort sort) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        categoryRepository.delete(category);
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category create(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public Category getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return category;
    }
}
