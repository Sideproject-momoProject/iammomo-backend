package dev.momo.api.question;

import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.question.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto createQuestion(Long categoryId, QuestionDto dto) throws CategoryNotFoundException;
    List<QuestionDto> readAllQuestion(Long categoryId) throws CategoryNotFoundException, QuestionNotFoundException;
    QuestionDto readQuestion(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException;
    boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) throws CategoryNotFoundException, QuestionNotFoundException, InvalidParamException;
    boolean deleteQuestion(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException;

}
