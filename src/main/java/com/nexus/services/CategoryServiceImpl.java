package com.nexus.services;

import com.nexus.entities.Category;
import com.nexus.interfaces.CategoryService;
import com.nexus.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByID(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("category not found"));
    }
}
