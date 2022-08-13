package dev.sideproject.momo.service;

import dev.sideproject.momo.model.JwtUserDTO;
import dev.sideproject.momo.model.UserPostInterface;

import java.util.Collection;

public interface UserService {

//    Collection<JwtUserDTO> readAll();
//    boolean update(JwtUserDTO dto, Long id);
//    boolean delete(Long id);

    Collection<UserPostInterface> findByUserPost(Long id);

}
