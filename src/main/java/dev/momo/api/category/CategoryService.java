package dev.momo.api.category;

import dev.momo.api.category.dto.CategoryDto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.momo.api.global.exception.CategoryNotFoundException;


import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);

    List<CategoryDto> readAllCategory() throws CategoryNotFoundException;
    CategoryDto readCategory(Long categoryId) throws CategoryNotFoundException;

    boolean updateCategory(Long categoryId, CategoryDto dto) throws CategoryNotFoundException;
    boolean deleteCategory(Long categoryId) throws CategoryNotFoundException;

    List<CategoryDto> readAllCategory1(Pageable pageable);
}
