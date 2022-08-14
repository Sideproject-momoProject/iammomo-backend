package dev.momo.api.board.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    private String comment;

    @Builder.Default
    private boolean isUpdated = false;
    @Builder.Default
    private boolean isDeleted = false;

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

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Post.class
    )
    @JoinColumn(name = "post_Id")
    private Post post;

    protected Comment(){

    }

    @Builder
    public Comment(Long commentId, String comment, boolean isUpdated, boolean isDeleted, Category category, Question question, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
        this.category = category;
        this.question = question;
        this.post = post;
    }
}
