package com.nexus.controllers;

import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;
import com.nexus.interfaces.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public CategoryDTO createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryByID(id);
    }
}
