package dev.sideproject.momo.entity;


import javax.persistence.*;

@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String topic;


    //user
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = JwtUserEntity.class
    )
    @JoinColumn(name = "user_id")
    private JwtUserEntity writer;

    public PostEntity() {
    }

    public PostEntity(Long id, String content, String topic, JwtUserEntity writer) {
        this.id = id;
        this.content = content;
        this.topic = topic;
        this.writer = writer;
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

    public JwtUserEntity getWriter() {
        return writer;
    }

    public void setWriter(JwtUserEntity writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", topic='" + topic + '\'' +
                ", writer=" + writer +
                '}';
    }
}
