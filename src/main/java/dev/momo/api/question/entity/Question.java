package dev.momo.api.question.entity;

import dev.momo.api.category.entity.Category;
import dev.momo.api.global.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Entity
@Table(name = "quesiton")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    private String question;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Category.class
    )
    @JoinColumn(name = "category_Id")
    private Category category;

    protected Question() {
    }

    @Builder
    public Question(Long questionId, String question,Category category) {
        this.questionId = questionId;
        this.question = question;
        this.category = category;
    }

}
