package com.nexus.controllers;

import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;
import com.nexus.interfaces.CategoryService;
import com.nexus.mappers.CategoryMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper categoryMapper = new ModelMapper();

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategories(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryDTO> categories = categoryService.getAll(pageable)
                .map(category -> categoryMapper.map(category, CategoryDTO.class));
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }
}
