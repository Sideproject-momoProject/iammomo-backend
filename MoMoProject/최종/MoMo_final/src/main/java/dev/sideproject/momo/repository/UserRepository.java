package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.UserEntity;
import dev.sideproject.momo.model.UserPostInterface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT p.topic, p.content, p.create_at, p.id \n" +
                    "FROM user_momo u\n" +
                    "    INNER JOIN post p on u.id = p.user_id\n" +
                    "where u.id = :id"
    )
    List<UserPostInterface> findByUserPost(@PathVariable("id") Long id);




}
