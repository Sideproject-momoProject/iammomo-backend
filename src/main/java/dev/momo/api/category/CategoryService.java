package dev.momo.api.category;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.global.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> readAllCategory() throws CategoryNotFoundException;
    CategoryDto readCategory(Long categoryId) throws CategoryNotFoundException;
    boolean updateCategory(Long categoryId, CategoryDto dto);
    boolean deleteCategory(Long categoryId);
}
