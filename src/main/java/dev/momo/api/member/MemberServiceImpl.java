package dev.momo.api.member;

import dev.momo.api.member.dto.MemberDto;
import dev.momo.api.member.entity.Member;
import dev.momo.api.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        Member member1 = Member.builder()
                .email("nana@nate.com")
                .password("1234")
                .memberStatus("1")
                .role("ROLE_USER")
                .build();
        Member member2 = Member.builder()
                .email("bora@nate.com")
                .password("1234")
                .memberStatus("1")
                .role("ROLE_USER")
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    @Override
    public List<MemberDto> findAllMember() {
        return memberRepository.findAll().stream()
                .map(member -> MemberDto.builder()
                        .email(member.getEmail())
                        .role(member.getRole())
                        .build())
                .collect(Collectors.toList());
    }
}
