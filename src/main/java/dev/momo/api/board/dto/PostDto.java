package dev.momo.api.board.dto;

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
