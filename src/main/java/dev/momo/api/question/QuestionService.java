package dev.momo.api.question;

import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.question.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto createQuestion(Long categoryId, QuestionDto dto) throws CategoryNotFoundException;
    List<QuestionDto> readAllQuestion(Long categoryId);
    QuestionDto readQuestion(Long categoryId, Long questionId);
    boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto);
    boolean deleteQuestion(Long categoryId, Long questionId);

}
