package org.example.gestionrh.Mapper;

import org.example.gestionrh.Entites.DTO.RoleEnum;
import org.example.gestionrh.Entites.DTO.UserCreateDTO;
import org.example.gestionrh.Entites.DTO.UserDto;
import org.example.gestionrh.Entites.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public UserDto toDTO(User user) {


        return UserDto.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .matricule(user.getMatricule())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRoleEnum())
                .build();
    }


    public  User toEntity(UserCreateDTO dto) {


        return User.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .matricule(dto.getMatricule())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .roleEnum(dto.getRole())
                .build();
    }

    public User UpdateUser(User user,UserDto dto){

        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setMatricule(dto.getMatricule());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRoleEnum(dto.getRole());

        return user;

    }
}
