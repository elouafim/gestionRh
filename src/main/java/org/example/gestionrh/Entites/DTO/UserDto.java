package org.example.gestionrh.Entites.DTO;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String keyckoakid;
    private String matricule;
    private String email;
    private String phone;
    private RoleEnum role;

    private int annee;
    private int totalConges;
    private int congesPris;
    private int congesRestants;
}
