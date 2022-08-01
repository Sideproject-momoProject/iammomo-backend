package dev.sideproject.momo.domain.post;


import dev.sideproject.momo.domain.BaseEntity;
import dev.sideproject.momo.domain.topic.TopicEntity;
import dev.sideproject.momo.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "post")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String content;

    //topic
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = TopicEntity.class
    )
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;


    //user
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "user_id")
    private UserEntity writer;



}
