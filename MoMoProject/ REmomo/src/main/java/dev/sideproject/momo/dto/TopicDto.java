package dev.sideproject.momo.dto;

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
public class TopicDto {
    @ApiModelProperty(value = "고유 id")
    private Long id;

    @ApiModelProperty(value = "질문" , example = "topic(질문): 기분이 어떤가요?")
    private String title;

    @ApiModelProperty(value = "생성시간")
    private Instant createAt;

}
