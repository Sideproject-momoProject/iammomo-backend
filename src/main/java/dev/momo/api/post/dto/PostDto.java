package dev.momo.api.post.dto;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.question.dto.QuestionDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDto {
    private Long postId;
    private String post;
    private CategoryDto categoryDto;
    private QuestionDto questionDto;
    private boolean isStatus;
    private boolean isDelete;

    @Builder
    public PostDto(Long postId, String post, CategoryDto categoryDto, QuestionDto questionDto, boolean isStatus, boolean isDelete) {
        this.postId = postId;
        this.post = post;
        this.categoryDto = categoryDto;
        this.questionDto = questionDto;
        this.isStatus = isStatus;
        this.isDelete = isDelete;
    }
}
