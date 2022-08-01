package dev.sideproject.momo.controller;

import dev.sideproject.momo.model.PostDto;
import dev.sideproject.momo.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @ApiOperation(value = "게시글 생성", notes = "content, topic, userId*** 넣기 (1.user & topic 먼저 생성 후 2. post 생성가능)")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto dto){
        PostDto result = this.postService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @ApiOperation(value = "전체 게시글 조회")
    public ResponseEntity<Collection<PostDto>> readPostAll(){
        Collection<PostDto> postList = this.postService.readAll();
        if (postList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(postList);
    }

    @GetMapping("{postId}")
    @ApiOperation(value = "단일 게시글 조회")
    public ResponseEntity<PostDto> readPost(
            @RequestParam("postId") Long postId){
        PostDto postDto = this.postService.read(postId);
        if (postDto == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(postDto);
    }

    @PutMapping("{postId}")
    @ApiOperation(value = "게시글 수정", notes = "content , topic, userId*** 넣기 ")
    public ResponseEntity<?> updatePost(
            @RequestBody PostDto dto,
            @PathVariable("postId") Long postId
           ){
        if (!postService.update(dto, postId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<?> deletePost(
            @PathVariable("postId") Long postId){
        if (!postService.delete(postId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }





}
