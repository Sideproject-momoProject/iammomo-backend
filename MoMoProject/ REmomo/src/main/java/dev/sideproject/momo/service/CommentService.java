package dev.sideproject.momo.service;

import dev.sideproject.momo.dto.CommentDto;

import java.util.Collection;

public interface CommentService {

    CommentDto create(Long postId, CommentDto dto);
    Collection<CommentDto> readAll(Long postId);
    boolean update(Long postId, CommentDto dto, Long commentId);
    boolean delete(Long postId, CommentDto dto, Long commentId);


}
