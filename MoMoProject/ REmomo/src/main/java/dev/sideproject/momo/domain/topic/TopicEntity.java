package dev.sideproject.momo.domain.topic;

import dev.sideproject.momo.domain.post.PostEntity;
import dev.sideproject.momo.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "topic")
public class TopicEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    private String title;


    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class,
            mappedBy = "topicEntity"
    )
    private List<PostEntity> postEntityList = new ArrayList<>();





}
