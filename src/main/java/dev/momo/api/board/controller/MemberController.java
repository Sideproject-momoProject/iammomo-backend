package dev.momo.api.board.controller;

import dev.momo.api.global.response.BaseResponse;
import dev.momo.api.member.MemberService;
import dev.momo.api.member.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public BaseResponse<List<MemberDto>> readAllMember() {
        return new BaseResponse<>(memberService.findAllMember());
    }
}
