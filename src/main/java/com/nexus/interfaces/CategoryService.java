package com.nexus.interfaces;

import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;

import java.util.List;


public interface CategoryService {
    public List<CategoryDTO> getCategories();

    public CategoryDTO createCategory(Category category);

    public CategoryDTO getCategoryByID(Long id);
}
