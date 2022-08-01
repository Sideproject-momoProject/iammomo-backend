package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.JwtUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtUserRepository extends JpaRepository<JwtUserEntity, String> {
    JwtUserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
    JwtUserEntity findByEmailAndPassword(String email, String password);
}
