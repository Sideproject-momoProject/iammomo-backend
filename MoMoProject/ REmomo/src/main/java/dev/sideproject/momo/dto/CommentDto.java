package dev.sideproject.momo.dto;

import dev.sideproject.momo.domain.post.PostEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder //package-private 생성자가 자동으로 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어준다
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Data
public class CommentDto {

    @ApiModelProperty(value = "고유 id")
    private Long id;

    @ApiModelProperty(value = "댓글" , example = "댓글: 정말 좋아보여요")
    private String comment;

    private Instant createAt;
    private Long userId;
    private PostDto postDto;


}
