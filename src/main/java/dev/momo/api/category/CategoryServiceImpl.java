package dev.momo.api.category;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional //트랜잭션은 데이터가 변경될 경우만 사용하기! 자원 낭비가 된다
    public CategoryDto createCategory(CategoryDto dto) {
        //CategoryEntity setter 안쓰고 저장하는법! -> builder 사용
        Category category = Category.builder()
                .categoryId(dto.getCategoryId())
                .category(dto.getCategory())
                .build();
        Category result = categoryRepository.save(category);
        //반환하기 위해서 CategoryDto 형태로 생성
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(result.getCategoryId())
                .category(result.getCategory())
                .build();
        return categoryDto;
    }

    @Override
    public List<CategoryDto> readAllCategory() throws CategoryNotFoundException {
        if(categoryRepository.findAll().isEmpty())
            throw new CategoryNotFoundException();

        return categoryRepository.findAll().stream()
                .map(category -> CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .category(category.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto readCategory(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new CategoryNotFoundException();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(category.get().getCategoryId())
                .category(category.get().getCategory())
                .build();
        return  categoryDto;
    }

    @Override
    @Transactional
    public boolean updateCategory(Long categoryId, CategoryDto dto) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Category category1 = Category.builder()
                .categoryId(category.get().getCategoryId())
                .category(dto.getCategory())
                .build();
        categoryRepository.save(category1);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        categoryRepository.delete(category.get());
        return true;
    }
}
