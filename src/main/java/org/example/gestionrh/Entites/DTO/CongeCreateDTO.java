package org.example.gestionrh.Entites.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Data
@Builder
public class CongeCreateDTO {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeConge typeConge;
    private Statut statut;
    private Long iduser;
    private String username;
    private String role;
}
