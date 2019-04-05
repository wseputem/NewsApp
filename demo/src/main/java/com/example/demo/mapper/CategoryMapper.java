package com.example.demo.mapper;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.persistence.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public CategoryDTO createCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription(categoryDTO.getDescription());
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
