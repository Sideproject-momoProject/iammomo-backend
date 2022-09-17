package dev.momo.api.member.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.momo.api.oauth.entity.ProviderType;
import dev.momo.api.oauth.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_MOMO")
public class User {
    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;


    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;


    @Column(name = "USERNAME", length = 100)
//    @NotNull
    @Size(max = 100)
    private String username;


//    @Column(name = "EMAIL", length = 512, unique = true)
//    @NotNull
    @Column(name = "EMAIL", length = 512)
    @Size(max = 512)
    private String email;


    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;


    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
//    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

//    public User(
//            @NotNull @Size(max = 64) String userId,
//            @NotNull @Size(max = 512) String email,
//            @NotNull @Size(max = 1) String emailVerifiedYn,
//            @NotNull ProviderType providerType,
//            @NotNull RoleType roleType,
//            @NotNull LocalDateTime createdAt,
//            @NotNull LocalDateTime modifiedAt
//    ) {
//        this.userId = userId;
//        this.email = email != null ? email : "NO_EMAIL";
//        this.password = "NO_PASS";
//        this.emailVerifiedYn = emailVerifiedYn;
//        this.providerType = providerType;
//        this.roleType = roleType;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//    }

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void Account(
            String email, String password, String emailVerifiedYn, ProviderType providerType, RoleType roleType,
            LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.userId = email;
        this.email = email;
        this.password = password;
        this.emailVerifiedYn = emailVerifiedYn;
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = "";
        this.profileImageUrl = "";

    }



}
