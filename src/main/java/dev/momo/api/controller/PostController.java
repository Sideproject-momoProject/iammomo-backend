package dev.momo.api.controller;

import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.global.response.BaseResponse;
import dev.momo.api.post.PostServiceImpl;
import dev.momo.api.post.dto.PostDto;
import dev.momo.api.question.dto.QuestionDto;
import org.springframework.web.bind.annotation.*;

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

}
