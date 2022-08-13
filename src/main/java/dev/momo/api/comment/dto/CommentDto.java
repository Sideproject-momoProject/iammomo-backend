package dev.momo.api.comment.dto;

import dev.momo.api.post.dto.PostDto;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class CommentDto {
    private Long commentId;
    private String comment;
    private boolean isUpdated;
    private boolean isDeleted;
    private Instant createAt;
    private Instant updateAt;
    private PostDto postDto;

    @Builder
    public CommentDto(Long commentId, String comment, boolean isUpdated, boolean isDeleted, Instant createAt, Instant updateAt, PostDto postDto) {
        this.commentId = commentId;
        this.comment = comment;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.postDto = postDto;
    }
}
