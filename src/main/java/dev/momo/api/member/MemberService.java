package dev.momo.api.member;

import dev.momo.api.member.dto.MemberDto;

import java.util.List;

public interface MemberService {
    List<MemberDto> findAllMember();
}
