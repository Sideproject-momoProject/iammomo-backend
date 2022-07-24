package dev.momo.api.question;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.question.dto.QuestionDto;
import dev.momo.api.question.entity.Question;
import dev.momo.api.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    private final QuestionRepository questionRepository; /** final 안붙이면 null에러 */
    private final CategoryRepository categoryRepository;


    @Override
    public QuestionDto createQuestion(Long categoryId, QuestionDto dto) throws CategoryNotFoundException {

        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.get();

        Question question = Question.builder()
                .questionId(dto.getQuestionId())
                .question(dto.getQuestion())
                .build();

        Question result = this.questionRepository.save(question);
        QuestionDto questionDto = QuestionDto.builder()
                .questionId(result.getQuestionId())
                .question(result.getQuestion())
                .createAt(result.getCreateAt()) //왜 null 이지
                .update(result.getUpdateAt()) //왜 null 이지
                .categoryDto(CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .category(category.getCategory())
                        .build())
                .build();
        return questionDto;
    }

    @Override
    public List<QuestionDto> readAllQuestion(Long categoryId) {
        return null;
    }

    @Override
    public QuestionDto readQuestion(Long categoryId, Long questionId) {
        return null;
    }

    @Override
    public boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) {
        return false;
    }

    @Override
    public boolean deleteQuestion(Long categoryId, Long questionId) {
        return false;
    }
}
