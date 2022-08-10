package dev.momo.api.comment;

import dev.momo.api.comment.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public CommentDto createComment(Long categoryId, Long questionId, Long postId, CommentDto dto) {
        return null;
    }

    @Override
    public List<CommentDto> readAllComment(Long categoryId, Long questionId, Long postId) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long categoryId, Long questionId, Long postId, Long commentId, CommentDto dto) {
        return null;
    }

    @Override
    public CommentDto deleteComment(Long categoryId, Long questionId, Long postId, Long commentId, CommentDto dto) {
        return null;
    }
}
