package org.example.gestionrh.Entites.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CongeDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeConge typeConge;
    private Statut statut;
    private Long iduser;
    private String username;
    private String role;
}
