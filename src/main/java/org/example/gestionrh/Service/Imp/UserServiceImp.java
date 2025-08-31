package org.example.gestionrh.Service.Imp;

import jakarta.transaction.Transactional;
import org.example.gestionrh.Entites.DTO.UserCreateDTO;
import org.example.gestionrh.Entites.DTO.UserDto;
import org.example.gestionrh.Entites.User;
import org.example.gestionrh.Mapper.UserMapper;
import org.example.gestionrh.Repository.UserRepository;
import org.example.gestionrh.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDto createUser(UserCreateDTO user) {

        try {
            String keykoakid=keycloakService.createUserWithRole(user.getNom(), user.getEmail(),user.getPassword(),user.getRole().toString());

            User user1= userMapper.toEntity(user);
            user1.setKeyckoakid(keykoakid);
            return  userMapper.toDTO(userRepository.save(user1));

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public UserDto UpdteUser(UserDto user) {
        try {
            Optional<User> user1=userRepository.findById(user.getId());
            if(user1.isPresent()){
                keycloakService.updateUserInfo(user.getKeyckoakid(), user.getEmail(),user.getRole().toString());
                return userMapper.toDTO(userRepository.save(userMapper.UpdateUser(user1.get(),user)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteUser(Long id) {
        try {

            Optional<User> user= userRepository.findById(id);
            if(user.isPresent()){
                keycloakService.deleteUser(user.get().getKeyckoakid());
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UserDto getUser(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO).orElse(null);
    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }


    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO).orElse(null);
    }
}
