package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.JwtUserEntity;
import dev.sideproject.momo.model.UserPostInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface JwtUserRepository extends JpaRepository<JwtUserEntity, Long> {
    JwtUserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
    JwtUserEntity findByEmailAndPassword(String email, String password);

    @Query(
            nativeQuery = true,
            value = "SELECT p.topic, p.content, p.create_at, p.id \n" +
                    "FROM momo u\n" +
                    "    INNER JOIN post p on u.u_id = p.user_id\n" +
                    "where u.u_id = :id"
    )
    List<UserPostInterface> findByUserPost(@PathVariable("id") Long id);





}
