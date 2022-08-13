package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.user.UserEntity;
import dev.sideproject.momo.dto.UserDto;
import dev.sideproject.momo.domain.user.UserPostInterface;
import dev.sideproject.momo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JpaUserService implements UserService{
    private final UserRepository userRepository;


    @Override
    public UserDto create(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(dto.getPassword());
        userEntity.setEmail(dto.getEmail());
        userEntity = this.userRepository.save(userEntity);

        return new UserDto(
                userEntity.getUid(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getCreateAt()
        );
    }

    @Override
    public Collection<UserDto> readAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(
                userEntity -> userDtoList.add( new UserDto(
                        userEntity.getUid(),
                        userEntity.getEmail(),
                        userEntity.getPassword(),
                        userEntity.getCreateAt()
                )));
        return userDtoList;
    }

    @Override
    public UserDto read(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        return new UserDto(
                userEntity.getUid(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getCreateAt()
        );
    }

    @Override
    public boolean update(UserDto dto, Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null? userEntity.getPassword() : dto.getPassword()
        );

        userEntity.setEmail(
                dto.getEmail() == null ? userEntity.getEmail() : dto.getEmail()
        );

        this.userRepository.save(userEntity);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        this.userRepository.delete(userEntity);
        return true;
    }


    @Override
    public Collection<UserPostInterface> findByUserPost(Long id) {
        List<UserPostInterface> userPostList = this.userRepository.findByUserPost(id);
        return userPostList;
    }


}
