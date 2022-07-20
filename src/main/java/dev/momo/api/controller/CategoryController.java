package dev.momo.api.controller;

import dev.momo.api.category.CategoryServiceImpl;
import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.global.exception.SearchResultNotFoundException;
import dev.momo.api.global.response.BaseResponse;
import dev.momo.api.global.response.BaseResponseStatus;
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
    public BaseResponse<CategoryDto> readCategory(@PathVariable("categoryId") Long categoryId) throws SearchResultNotFoundException {
       CategoryDto categoryDto = this.categoryServiceImpl.readCategory(categoryId);
       if (categoryDto == null)
           return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_CATEGORY_EXCEPTION);
       else
           return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PutMapping("{categoryId}")
    public BaseResponse<?> updateCategory(@PathVariable("categoryId")Long categoryId,
                                          @RequestBody CategoryDto dto){

        if(!categoryServiceImpl.updateCategory(categoryId, dto))
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_CATEGORY_EXCEPTION);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("{categoryId}")
    public BaseResponse<?> deleteCategory(@PathVariable("categoryId")Long categoryId){
        if (!categoryServiceImpl.deleteCategory(categoryId))
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_CATEGORY_EXCEPTION);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
