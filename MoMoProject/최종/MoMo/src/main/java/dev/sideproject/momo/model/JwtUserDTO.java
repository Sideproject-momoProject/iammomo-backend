package dev.sideproject.momo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDTO {

    @ApiModelProperty(value = "토큰" , example = "회원가입시에는 null")
    private String token;

    @ApiModelProperty(value = "이메일" , example = "ta@naveer.com")
    private String email;

    @ApiModelProperty(value = "성명", example = "짱구")
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "password")
    private String password;

    @ApiModelProperty(value = "고유 id")
    private String id;

    @ApiModelProperty(value = "Loong id")
    private Long uId;
}
