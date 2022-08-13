package dev.sideproject.momo.service;

import dev.sideproject.momo.model.PostDto;

import java.util.Collection;

public interface PostService {

    PostDto create( PostDto dto);
    PostDto read( Long postId);
    Collection<PostDto> readAll();
    boolean update(PostDto dto, Long postId);
    boolean delete(Long postId);

}
