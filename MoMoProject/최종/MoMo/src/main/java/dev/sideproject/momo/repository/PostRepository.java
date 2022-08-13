package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    
}
