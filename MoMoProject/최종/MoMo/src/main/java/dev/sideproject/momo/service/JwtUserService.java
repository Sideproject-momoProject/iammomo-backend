package dev.sideproject.momo.service;

import dev.sideproject.momo.entity.JwtUserEntity;
import dev.sideproject.momo.model.JwtUserDTO;
import dev.sideproject.momo.model.UserPostInterface;
import dev.sideproject.momo.repository.JwtUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JwtUserService implements UserService{

    @Autowired
    private JwtUserRepository userRepository;

    //유저생성 (회원가입)
    public JwtUserEntity create(final JwtUserEntity userEntity) {
        if(userEntity == null || userEntity.getEmail() == null ) {
            throw new RuntimeException("Invalid arguments");
        }
        final String email = userEntity.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(userEntity);
    }

    //로그인
    public JwtUserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final JwtUserEntity originalUser = userRepository.findByEmail(email);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }

    @Override
    public Collection<UserPostInterface> findByUserPost(Long id) {
        List<UserPostInterface> userPostList = this.userRepository.findByUserPost(id);
        return userPostList;
    }
}
