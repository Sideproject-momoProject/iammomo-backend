package dev.momo.api.controller;

import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.PostNotFoundException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.global.response.BaseResponse;
import dev.momo.api.global.response.BaseResponseStatus;
import dev.momo.api.post.PostServiceImpl;
import dev.momo.api.post.dto.PostDto;
import dev.momo.api.post.entity.Post;
import dev.momo.api.question.dto.QuestionDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories/{categoryId}/questions/{questionId}/posts")
public class PostController {
    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping()
    public BaseResponse<PostDto> createPost(@PathVariable("categoryId") Long categoryId,
                                            @PathVariable("questionId")Long questionId,
                                            @RequestBody PostDto dto) throws QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(postService.createPost(categoryId, questionId, dto));
    }

    @GetMapping()
    public BaseResponse<List<PostDto>> readAllPost(@PathVariable("categoryId")Long categoryId,
                                                  @PathVariable("questionId")Long questionId) throws QuestionNotFoundException, PostNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(postService.readAllPost(categoryId, questionId));
    }

    @GetMapping("{postId}")
    public BaseResponse<PostDto> readPost(@PathVariable("categoryId")Long categoryId,
                                          @PathVariable("questionId")Long questionId,
                                          @PathVariable("postId")Long postId) throws QuestionNotFoundException, CategoryNotFoundException {
        return new BaseResponse<>(postService.readPost(categoryId,questionId, postId));
    }

    //update
    @PostMapping("{postId}")
    public BaseResponse<PostDto> updatePost(@PathVariable("categoryId")Long categoryId,
                                      @PathVariable("questionId")Long questionId,
                                      @PathVariable("postId")Long postId,
                                      @RequestBody PostDto dto) throws QuestionNotFoundException, CategoryNotFoundException, PostNotFoundException, InvalidParamException {
        return new BaseResponse<>(postService.updatePost(categoryId,questionId,postId,dto));
    }

    //delete
    @PostMapping("/delete/{postId}")
    public BaseResponse<?> deletePost(@PathVariable("categoryId")Long categoryId,
                                            @PathVariable("questionId")Long questionId,
                                            @PathVariable("postId")Long postId) throws InvalidParamException, QuestionNotFoundException, CategoryNotFoundException {
        if (!postService.deletePost(categoryId, questionId, postId))
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_POST_EXCEPTION);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
