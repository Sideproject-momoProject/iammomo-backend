package dev.momo.api.member.service;


import dev.momo.api.global.exception.BadRequestException;
import dev.momo.api.member.entity.User;
import dev.momo.api.member.repository.UserRepository;
import dev.momo.api.oauth.entity.ProviderType;
import dev.momo.api.oauth.entity.RoleType;
import dev.momo.api.oauth.info.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void checkPassword(String password, String encodedPassword) {
        boolean isSame = passwordEncoder.matches(password, encodedPassword);
        if(!isSame) {
            throw new BadRequestException("아이디 혹은 비밀번호를 확인하세요.");
        }
    }

    @Transactional
    public void signUp(String email, String password) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        ProviderType providerType = ProviderType.valueOf("LOCAL".toUpperCase());

        checkEmailIsDuplicate(email);
        String encodedPassword = passwordEncoder.encode(password);

//        User user = new User(
//                    email,
//                    email,
//                    "Y",
//                    providerType,
//                    RoleType.USER,
//                    now,
//                    now
//        );

        user.Account(
                email,
                encodedPassword,
                "Y",
                providerType,
                RoleType.USER,
                now,
                now
        );

        userRepository.save(user);
    }

    private void checkEmailIsDuplicate(String email) {
        boolean isDuplicate = userRepository.existsByEmail(email);
        if(isDuplicate) {
            throw new BadRequestException("이미 존재하는 회원입니다.");
        }
    }


    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }


}