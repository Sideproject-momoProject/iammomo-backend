package dev.sideproject.momo.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;


public class TopicDto {
    @ApiModelProperty(value = "고유 id")
    private Long id;

    @ApiModelProperty(value = "질문" , example = "오늘 기분이 어때")
    private String title;
    @ApiModelProperty(value = "생성시간")
    private Instant createAt;

    public TopicDto() {
    }

    public TopicDto(Long id, String title, Instant createAt) {
        this.id = id;
        this.title = title;
        this.createAt = createAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
