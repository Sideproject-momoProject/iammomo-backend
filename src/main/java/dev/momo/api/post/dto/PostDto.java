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
    private boolean isStatus;
    private boolean isDelete;
    private QuestionDto questionDto;


    @Builder
    public PostDto(Long postId, String post,  boolean isStatus, boolean isDelete, QuestionDto questionDto) {
        this.postId = postId;
        this.post = post;
        this.isStatus = isStatus;
        this.isDelete = isDelete;
        this.questionDto = questionDto;

    }
}
