package dev.momo.api.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.global.response.BaseResponse;
import dev.momo.api.question.QuestionServiceImpl;
import dev.momo.api.question.dto.QuestionDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories/{categoryId}/questions")
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public BaseResponse<QuestionDto> createQuestion(@PathVariable("categoryId")Long categoryId,
                                                    @RequestBody QuestionDto dto) throws CategoryNotFoundException {

        return new BaseResponse<>(questionService.createQuestion(categoryId, dto));
    }

    @GetMapping()
    public BaseResponse<List<QuestionDto>> readAllQuestion(@PathVariable("categoryId")Long categoryId) throws QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(questionService.readAllQuestion(categoryId));
    }

    @GetMapping("{questionId}")
    public BaseResponse<QuestionDto> readQuestion(@PathVariable("categoryId")Long categoryId,
                                                  @PathVariable("questionId")Long questionId) throws QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(questionService.readQuestion(categoryId, questionId));
    }

    @PutMapping("{questionId}")
    public BaseResponse<?>updateQuestion(@PathVariable("categoryId")Long categoryId,
                                         @PathVariable("questionId")Long questionId,
                                         @RequestBody QuestionDto dto) throws InvalidParamException, QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(questionService.updateQuestion(categoryId, questionId, dto));
    }

    @DeleteMapping("{questionId}")
    public BaseResponse<?>deleteQuestion(@PathVariable("categoryId")Long categoryId,
                                         @PathVariable("questionId")Long questionId) throws QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(questionService.deleteQuestion(categoryId, questionId));
    }

}
