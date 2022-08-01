package dev.sideproject.momo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_momo")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String username;

    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPosts = new ArrayList<>();

    public UserEntity() {
    }


    public UserEntity(Long id, String email, String password, String username, List<PostEntity> writtenPosts) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.writtenPosts = writtenPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PostEntity> getWrittenPosts() {
        return writtenPosts;
    }

    public void setWrittenPosts(List<PostEntity> writtenPosts) {
        this.writtenPosts = writtenPosts;
    }
}
