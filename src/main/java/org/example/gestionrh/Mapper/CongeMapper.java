package org.example.gestionrh.Mapper;

import org.example.gestionrh.Entites.Conge;
import org.example.gestionrh.Entites.DTO.CongeCreateDTO;
import org.example.gestionrh.Entites.DTO.CongeDTO;
import org.example.gestionrh.Entites.DTO.RoleEnum;
import org.example.gestionrh.Entites.User;
import org.example.gestionrh.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CongeMapper {

    @Autowired
    private UserRepository userRepository;

    public Conge toEntity(CongeCreateDTO dto) {

        User user=userRepository.findById(dto.getIduser()).orElse(null);

        return Conge.builder()
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .typeConge(dto.getTypeConge())
                .statut(dto.getStatut())
                .user(user)
                .build();
    }

    public CongeDTO toDTO(Conge entity) {

        User user=entity.getUser();

        return CongeDTO.builder()
                .id(entity.getId())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .typeConge(entity.getTypeConge())
                .statut(entity.getStatut())
                .iduser(user.getId())
                .username(user.getPrenom())
                .role(user.getRoleEnum().toString())
                .build();
    }

    public void updateEntityFromDTO(CongeDTO dto, Conge entity) {
        User user=userRepository.findById(dto.getIduser()).orElse(null);
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setTypeConge(dto.getTypeConge());
        entity.setStatut(dto.getStatut());
        entity.getUser().setPrenom(dto.getUsername());
        entity.getUser().setRoleEnum(RoleEnum.valueOf(dto.getRole()));
    }
}

