package dev.momo.api.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "memeber")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String email;
    private String password;

    @Column(name = "member_status")
    private String memberStatus;
    private String role;

    public Member() {
    }

    @Builder
    public Member(Long memberId, String email, String password, String memberStatus, String role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.memberStatus = memberStatus;
        this.role = role;
    }
}
