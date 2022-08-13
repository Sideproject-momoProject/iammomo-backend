package dev.momo.api.post.dto;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.question.dto.QuestionDto;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PostDto {
    private Long postId;
    private String post;
    private boolean isUpdated;
    private boolean isDeleted;
    private Instant createAt;
    private Instant updateAt;
    private QuestionDto questionDto;


    @Builder
    public PostDto(Long postId, String post, boolean isUpdated, boolean isDeleted, Instant createAt, Instant updateAt, QuestionDto questionDto) {
        this.postId = postId;
        this.post = post;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.questionDto = questionDto;
    }
}
