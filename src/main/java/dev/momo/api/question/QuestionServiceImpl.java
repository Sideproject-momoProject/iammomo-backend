package dev.momo.api.question;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.question.dto.QuestionDto;
import dev.momo.api.question.entity.Question;
import dev.momo.api.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .createAt(result.getCreateAt())
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
                .createAt(question.getCreateAt()) //todo : null값 해결 필요 - 부모클래스의 값을 가져오는 방법
                .update(question.getUpdateAt())
                .categoryDto(categoryDto)
                .build();

        return questionDto;
    }

    @Override
    @Transactional
    public boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) throws CategoryNotFoundException, QuestionNotFoundException, InvalidParamException {
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

       if (!questionRepository.existsById(questionId))
           throw new QuestionNotFoundException(); //질문 존재여부확인

        Optional<Question> questionOptional = questionRepository.findById(questionId);
       if (!questionOptional.get().getQuestionId().equals(questionId)) //저장된 값이 맞는지 확인 여부
            throw new InvalidParamException();

       Question saveData = Question.builder()
               .questionId(questionOptional.get().getQuestionId())
               .question( dto.getQuestion()== null ? questionOptional.get().getQuestion() : dto.getQuestion())
               .build();

       logger.info("들어온 dto 값{}.",dto.getQuestion());
       logger.info("기존의 값{}.",questionOptional.get().getQuestion());
       this.questionRepository.save(saveData);
       return true;
    }

    @Override
    public boolean deleteQuestion(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException {
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Optional<Question>questionOptional = questionRepository.findById(questionId);
        questionRepository.delete(questionOptional.get());
        return true;
    }
}
