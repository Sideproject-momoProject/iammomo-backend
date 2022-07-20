package dev.momo.api.category;

import dev.momo.api.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> readAllCategory();
    CategoryDto readCategory(Long categoryId);
    boolean updateCategory(Long categoryId, CategoryDto dto);
    boolean deleteCategory(Long categoryId);
}
