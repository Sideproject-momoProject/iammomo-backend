package dev.momo.api.post;

import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.PostNotFoundException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.post.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(Long categoryId, Long questionId,  PostDto dto) throws CategoryNotFoundException, QuestionNotFoundException;
    List<PostDto> readAllPost(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException, PostNotFoundException;
    PostDto readPost(Long categoryId, Long questionId, Long postId) throws CategoryNotFoundException, QuestionNotFoundException;
    PostDto updatePost(Long categoryId, Long questionId, Long postId, PostDto dto) throws CategoryNotFoundException, QuestionNotFoundException, PostNotFoundException, InvalidParamException;
    boolean deletePost(Long categoryId, Long questionId, Long postId) throws CategoryNotFoundException, QuestionNotFoundException, InvalidParamException;
}
