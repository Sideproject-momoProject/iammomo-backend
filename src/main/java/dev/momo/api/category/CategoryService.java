package dev.momo.api.category;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.global.exception.SearchResultNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> readAllCategory();
   // List<CategoryDto> readAllCategory(int offset, int limit);
    CategoryDto readCategory(Long categoryId) throws SearchResultNotFoundException;
    boolean updateCategory(Long categoryId, CategoryDto dto);
    boolean deleteCategory(Long categoryId);

    List<CategoryDto> readAllCategory1(Pageable pageable);
}
