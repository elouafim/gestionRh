package org.example.gestionrh.Entites.DTO;


import lombok.Data;

@Data
public class UserCreateDTO {
    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String phone;
    private String password;
    private RoleEnum role;

    private int annee;
    private int totalConges;
}
