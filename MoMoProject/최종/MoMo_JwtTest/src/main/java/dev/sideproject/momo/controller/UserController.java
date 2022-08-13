package dev.sideproject.momo.controller;


import dev.sideproject.momo.model.UserDto;
import dev.sideproject.momo.model.UserPostInterface;
import dev.sideproject.momo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "유저 생성(회원가입)", notes = "email, username, password 만")
    public ResponseEntity<UserDto> createUser
            (@RequestBody UserDto userDto){
        UserDto result = this.userService.create(userDto);
        return  ResponseEntity.ok(result);
    }

    @GetMapping
    @ApiOperation(value = "전체 유저 조회")
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        Collection<UserDto> userDtoList = userService.readAll();
        if (userDtoList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "단일 유저 조회")
    @ApiImplicitParam(name = "id", value = "유저아이디", required = true, paramType = "path")
    public ResponseEntity<UserDto> readUser(
            @PathVariable Long id){
        return ResponseEntity.ok(this.userService.read(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "유저 수정", notes = "email, password, username 수정가능")
    public ResponseEntity<?> updateUser(
            @RequestBody UserDto dto,
            @PathVariable Long id){
        this.userService.update(dto, id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "유저 삭제", notes = "응답값에 있는 id는 post의 id를 의미")
    @ApiImplicitParam(name = "id", value = "유저아이디" ,required = true, paramType = "path")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") Long id){
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/post")
    @ApiOperation(value = "내 게시글 확인")
    @ApiImplicitParam(name = "id", value = "유저아이디", required = true, paramType = "path")
    public ResponseEntity<Collection<UserPostInterface>> userPost(
            @PathVariable Long id){

        Collection<UserPostInterface> result = this.userService.findByUserPost(id);
        return  ResponseEntity.ok(result);
    }


}
