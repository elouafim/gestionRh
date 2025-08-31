package org.example.gestionrh.Service;

import org.example.gestionrh.Entites.DTO.UserCreateDTO;
import org.example.gestionrh.Entites.DTO.UserDto;
import org.springframework.data.domain.Page;

public interface IUserService {

 UserDto createUser(UserCreateDTO user);
 UserDto UpdteUser(UserDto user);
 boolean deleteUser(Long id);
 UserDto getUser(Long id);
 Page<UserDto> getAllUsers(int page, int size);
 UserDto getUserByEmail(String email);

}
