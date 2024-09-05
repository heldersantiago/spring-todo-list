package com.nexus.interfaces;

import com.nexus.entities.Category;

import java.util.List;


public interface CategoryService {
    public List<Category> getCategories();

    public Category createCategory(Category category);

    public Category getCategoryByID(Long id);
}
