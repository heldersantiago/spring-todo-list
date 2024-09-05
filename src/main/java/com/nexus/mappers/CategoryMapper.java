package com.nexus.mappers;


import com.nexus.dtos.CategoryDTO;
import com.nexus.entities.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle()
        );
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setTitle(categoryDTO.getTitle());
        return category;
    }
}