package dev.sideproject.momo.controller;

import dev.sideproject.momo.dto.CommentDto;
import dev.sideproject.momo.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping()
    @ApiOperation(value= "댓글 생성", notes = "comment, userId")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto){

        CommentDto result = this.commentService.create(postId, dto);
        return ResponseEntity.ok(result);
    }


    //테스트 확인용
    @GetMapping()
    @ApiOperation(value="Test 확인용")
    public ResponseEntity<Collection<CommentDto>> readCommentAll(
            @PathVariable("postId") Long postId){
        Collection<CommentDto> commentDtoList = this.commentService.readAll(postId);
        if (commentDtoList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(commentDtoList);

    }


    @PutMapping("{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "comment, userId")
    public ResponseEntity<?> updateComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){

        if (!commentService.update(postId, dto, commentId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{commentId}")
    @ApiOperation(value = "댓글 삭제")
    public ResponseEntity<?> deleteComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){

        if (!commentService.delete(postId, dto, commentId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }




}
