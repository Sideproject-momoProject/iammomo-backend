package dev.momo.api.member.controller;

import dev.momo.api.global.oauth2_common.ApiResponse;
import dev.momo.api.global.oauth2_common.BasicResponse;
import dev.momo.api.member.dto.LoginResponseDto;
import dev.momo.api.member.dto.SignUpRequestDto;
import dev.momo.api.member.entity.AuthReqModel;
import dev.momo.api.member.entity.User;
import dev.momo.api.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<BasicResponse> signUp(@RequestBody AuthReqModel signUpUser) {
        userService.signUp(signUpUser.getId(), signUpUser.getPassword());
        BasicResponse response = new BasicResponse("회원가입 성공", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthReqModel loginDto) {
//        LoginResponseDto responseDto = userService.login(loginDto.getId(), loginDto.getPassword());
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }


    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);

    }

}