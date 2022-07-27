package dev.momo.api.question.dto;

import dev.momo.api.category.dto.CategoryDto;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class QuestionDto {
    private Long questionId;
    private String question;
    private Instant createAt;
    private Instant update;
    private CategoryDto categoryDto;

    @Builder
    public QuestionDto(Long questionId, String question, Instant createAt, Instant update, CategoryDto categoryDto) {
        this.questionId = questionId;
        this.question = question;
        this.createAt = createAt;
        this.update = update;
        this.categoryDto = categoryDto;
    }

}
