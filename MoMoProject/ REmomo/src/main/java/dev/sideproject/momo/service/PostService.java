package dev.sideproject.momo.service;

import dev.sideproject.momo.dto.PostDto;

import java.util.Collection;

public interface PostService {

    PostDto create(Long topicId, PostDto dto);
    PostDto read(Long topicId, Long postId);
    Collection<PostDto> readAll(Long topicId);
    boolean update(Long topicId, PostDto dto, Long postId);
    boolean delete(Long topicId, Long postId);

}
