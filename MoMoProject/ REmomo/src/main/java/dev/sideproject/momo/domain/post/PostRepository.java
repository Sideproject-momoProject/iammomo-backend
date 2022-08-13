package dev.sideproject.momo.domain.post;

import dev.sideproject.momo.domain.post.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {


}
