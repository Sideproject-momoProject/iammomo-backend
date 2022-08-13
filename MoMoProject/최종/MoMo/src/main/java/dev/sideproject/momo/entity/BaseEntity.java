package dev.sideproject.momo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Table(name = "time")
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //누가 수정하지 못하도록 막는역할
    private Instant createAt;

    @LastModifiedDate
    private Instant updateAt;

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

}
