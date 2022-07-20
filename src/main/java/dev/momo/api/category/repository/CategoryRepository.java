package dev.momo.api.category.repository;

import dev.momo.api.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
                                            //CrudRepository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findCategoriesBy(Pageable pageable);
}
