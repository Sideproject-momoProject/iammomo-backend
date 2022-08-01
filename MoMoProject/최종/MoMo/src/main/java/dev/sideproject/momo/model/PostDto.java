package dev.sideproject.momo.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

public class PostDto {
    @ApiModelProperty(value = "고유 id")
    private Long id;

    @ApiModelProperty(value = "질문답변" , example = "오늘은 좋았어요")
    private String content;

    @ApiModelProperty(value = "질문" , example = "기분이 어떤가요?")
    private String topic;

    private Long userId;
    private Instant createAt;
//    private Instant updateAt;


    public PostDto() {
    }

    public PostDto(Long id, String content, String topic, Long userId, Instant createAt) {
        this.id = id;
        this.content = content;
        this.topic = topic;
        this.userId = userId;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }
}
