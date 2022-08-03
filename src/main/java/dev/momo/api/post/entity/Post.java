package dev.momo.api.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.momo.api.category.entity.Category;
import dev.momo.api.global.BaseEntity;
import dev.momo.api.question.entity.Question;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "post")
public class Post  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    private String post;

    @Builder.Default
    private boolean isStatus = false;
    @Builder.Default
    private boolean isDelete = false;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Category.class
    )
    @JoinColumn(name = "category_Id")
    private Category category;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Question.class
    )
    @JoinColumn(name = "question_Id")
    private Question question;

    protected Post() {
    }

    @Builder
    public Post(long postId, String post, boolean isStatus, boolean isDelete, Category category, Question question) {
        this.postId = postId;
        this.post = post;
        this.isStatus = isStatus;
        this.isDelete = isDelete;
        this.category = category;
        this.question = question;
    }
}
