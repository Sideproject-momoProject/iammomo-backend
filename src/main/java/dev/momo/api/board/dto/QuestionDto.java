package dev.momo.api.board.dto;

import dev.momo.api.board.dto.CategoryDto;
import dev.momo.api.board.entity.Question;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class QuestionDto {
    private Long questionId;
    private String question;
    private Instant createAt;
    private Instant updateAt;
    private CategoryDto categoryDto;

    @Builder
    public QuestionDto(Long questionId, String question, Instant createAt, Instant updateAt, CategoryDto categoryDto) {
        this.questionId = questionId;
        this.question = question;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.categoryDto = categoryDto;
    }

    public static QuestionDto convertToDto(Question question){
        QuestionDto convert = QuestionDto.builder()
                .questionId(question.getQuestionId())
                .question(question.getQuestion())
                .build();
        return convert;
    }

}
