package com.nexus.services;

import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;
import com.nexus.interfaces.CategoryService;
import com.nexus.mappers.CategoryMapper;
import com.nexus.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(savedCategory);
    }


    @Override
    public CategoryDTO getCategoryByID(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return CategoryMapper.toDTO(category);
    }
}
