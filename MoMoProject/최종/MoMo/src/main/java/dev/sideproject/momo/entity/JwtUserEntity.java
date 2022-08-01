package dev.sideproject.momo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "momo", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class JwtUserEntity {


    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; // 유저에게 고유하게 부여되는 id.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;


    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPosts = new ArrayList<>();




    @Column(name = "username", nullable = false)
    private String username; // 유저의 이름

    @Column(name = "email", nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.

    @Column(name = "password", nullable = false)
    private String password; // 패스워드. null이 가능한 이유는 oAuth로 페이스북이나 트위터같은 제3의 어플리케이션을 통해 로그인 할 수 있게 하기 위함이다.
}