package dev.sideproject.momo.controller;

import dev.sideproject.momo.dto.TopicDto;
import dev.sideproject.momo.service.TopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/topic")
public class TopicController {

    private final TopicService topicService;


    public TopicController(
            @Autowired TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @ApiOperation(value = "질문 생성", notes = "title 만")
    public ResponseEntity<TopicDto> createTopic
            (@RequestBody TopicDto dto){
        return ResponseEntity.ok(topicService.create(dto));
    }

    @GetMapping
    @ApiOperation(value = "전체 질문 조회")
    public ResponseEntity<Collection<TopicDto>> readTopicAll(){
        return ResponseEntity.ok(topicService.readAll());
    }

    @GetMapping("{topicId}")
    @ApiOperation(value = "단일 질문 조회")
    public ResponseEntity<TopicDto> readTopic
            (@PathVariable("topicId") Long id){

        TopicDto dto = topicService.read(id);

        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{topicId}")
    @ApiOperation(value = "단일 질문 수정", notes = "title 만")
    public ResponseEntity<?> updateTopic
            (@PathVariable("topicId") Long id,
            @RequestBody TopicDto dto){

        if (!topicService.update(id, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{topicId}")
    @ApiOperation(value = "질문 삭제")
    public ResponseEntity<?> deleteTopic
            (@PathVariable("topicId") Long id){
        if (!topicService.delete(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }




}
