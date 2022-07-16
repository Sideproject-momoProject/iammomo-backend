package dev.momo.api.controller;

import dev.momo.api.category.CategoryServiceImpl;
import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping
    public BaseResponse<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        return new BaseResponse<>(categoryServiceImpl.createCategory(dto));
    }

    @GetMapping
    public BaseResponse<List<CategoryDto>> readAllCategory(@RequestBody CategoryDto dto){
        return new BaseResponse<>(categoryServiceImpl.readAllCategory());
    }

    @GetMapping("{categoryId}")
    public BaseResponse<CategoryDto> readCategory(@PathVariable("categoryId") Long categoryId){
        return new BaseResponse<>(categoryServiceImpl.readCategory(categoryId));

    }

    @PutMapping("{categoryId}")
    public BaseResponse<?> updateCategory(@PathVariable("categoryId")Long categoryId,
                                          @RequestBody CategoryDto dto){
        return new BaseResponse<>(categoryServiceImpl.updateCategory(categoryId, dto));
    }

    @DeleteMapping("{categoryId}")
    public BaseResponse<?> deleteCategory(@PathVariable("categoryId")Long categoryId){
        return new BaseResponse<>(categoryServiceImpl.deleteCategory(categoryId));
    }

}
