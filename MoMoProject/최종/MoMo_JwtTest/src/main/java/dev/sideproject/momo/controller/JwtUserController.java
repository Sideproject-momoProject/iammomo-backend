package dev.sideproject.momo.controller;

import dev.sideproject.momo.entity.JwtUserEntity;
import dev.sideproject.momo.model.JwtResponseDTO;
import dev.sideproject.momo.model.JwtUserDTO;
import dev.sideproject.momo.security.TokenProvider;
import dev.sideproject.momo.service.JwtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class JwtUserController {

    @Autowired
    private JwtUserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    // Bean으로 작성해도 됨.
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody JwtUserDTO userDTO) {
        try {
            // 리퀘스트를 이용해 저장할 유저 만들기
            JwtUserEntity user = JwtUserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            // 서비스를 이용해 리파지토리에 유저 저장
            JwtUserEntity registeredUser = userService.create(user);
            JwtUserDTO responseUserDTO = JwtUserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            // 유저 정보는 항상 하나이므로 그냥 리스트로 만들어야하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴.
            return ResponseEntity.ok(responseUserDTO);
        } catch (Exception e) {

            // 예외가 나는 경우 bad 리스폰스 리턴.
            JwtResponseDTO responseDTO = JwtResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody JwtUserDTO userDTO) {
        JwtUserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);

        if(user != null){
            final String token = tokenProvider.create(user);
            final JwtUserDTO responseUsetDTO = JwtUserDTO.builder()
                    .email(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUsetDTO);
        }else {
            JwtResponseDTO responseDTO = JwtResponseDTO.builder()
                    .error("Login Failed.")
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }

    }
}