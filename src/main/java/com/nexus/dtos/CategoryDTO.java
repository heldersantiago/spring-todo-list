package com.nexus.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String title;

    // Constructor
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.title = name;
    }
}

