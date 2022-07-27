package dev.momo.api.question;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.question.dto.QuestionDto;
import dev.momo.api.question.entity.Question;
import dev.momo.api.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .createAt(result.getCreateAt()) //todo: null 값 원인 확인
                .update(result.getUpdateAt())
                .categoryDto(CategoryDto.builder()
                        .categoryId(category.getCategoryId())
                        .category(category.getCategory())
                        .build())
                .build();
        return questionDto;
    }

    @Override
    public List<QuestionDto> readAllQuestion(Long categoryId) throws CategoryNotFoundException, QuestionNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Category category = categoryOptional.get();

        if (questionRepository.findAll().isEmpty())
            throw new QuestionNotFoundException();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .category(category.getCategory())
                .build();

        return questionRepository.findAll().stream()
                .map(question -> QuestionDto.builder()
                        .questionId(question.getQuestionId())
                        .question(question.getQuestion())
                        .question(String.valueOf(question.getCreateAt()))
                        .question(String.valueOf(question.getUpdateAt()))
                        .categoryDto(categoryDto)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto readQuestion(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException {
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .category(category.getCategory())
                .build();

        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        Question question = questionOptional.get();

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(question.getQuestionId())
                .question(question.getQuestion())
                .categoryDto(categoryDto)
                .build();

        return questionDto;
    }

    @Override
    public boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) throws CategoryNotFoundException, QuestionNotFoundException {
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.get();

        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        Question question = questionOptional.get();

        return true;
    }

    @Override
    public boolean deleteQuestion(Long categoryId, Long questionId) {
        return false;
    }
}
