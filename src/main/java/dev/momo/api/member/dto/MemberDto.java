package dev.momo.api.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberDto {
    private String email;
    private String role;

    @Builder
    public MemberDto(String email, String role) {
        this.email = email;
        this.role = role;
    }
}
