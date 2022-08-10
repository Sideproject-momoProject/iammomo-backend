package dev.momo.api.comment;

import dev.momo.api.comment.dto.CommentDto;
import dev.momo.api.comment.entity.Comment;
import dev.momo.api.post.dto.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long categoryId, Long questionId,  Long postId, CommentDto dto);
    List<CommentDto> readAllComment(Long categoryId, Long questionId,  Long postId);
    CommentDto updateComment(Long categoryId, Long questionId,  Long postId, Long commentId, CommentDto dto);
    CommentDto deleteComment(Long categoryId, Long questionId,  Long postId, Long commentId, CommentDto dto);
}
