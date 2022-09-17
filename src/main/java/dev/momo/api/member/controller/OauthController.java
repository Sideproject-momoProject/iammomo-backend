//package dev.momo.api.member.controller;
//
//import dev.momo.api.member.dto.MemberDto;
//import dev.momo.api.oauth.entity.UserPrincipal;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/oauth/redirect")
//public class OauthController {
//
//    @PostMapping("/save")
//    public ResponseEntity saveAdditionalInfo(@RequestBody MemberDto request, UserPrincipal userPrincipal) {
//        log.info("이것은 요청받은 이메일입니다.=> {}",  request.getEmail());
//        log.info("이것은 접속한 유저의 정보입니다. =>{}" , userPrincipal.getUserId());
//        return ResponseEntity.ok().build();
//    }
//}
