package dev.sideproject.momo.domain.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT u.uid ,p.content, p.create_at, p.pid, t.tid, t.title\n" +
                    "    FROM user_momo u \n" +
                    "    INNER JOIN post p ON u.uid = p.user_id JOIN topic t ON t.tid = p.topic_id\n" +
                    "where u.uid = :id"
    )
    List<UserPostInterface> findByUserPost(@PathVariable("id") Long id);


}


//클래스가 상속받기 위해서는 interface imple (겍체 상속받을때는 impl)
//같은 인터페이스 끼리는 extends (같은애들)