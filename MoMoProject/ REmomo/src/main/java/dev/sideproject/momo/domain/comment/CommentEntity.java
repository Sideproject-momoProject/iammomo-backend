package dev.sideproject.momo.domain.comment;

import dev.sideproject.momo.domain.BaseEntity;
import dev.sideproject.momo.domain.post.PostEntity;
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
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String comment;

    //post
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class
    )
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    //user
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "user_id")
    private UserEntity writer;





}
