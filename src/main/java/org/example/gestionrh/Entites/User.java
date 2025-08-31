package org.example.gestionrh.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gestionrh.Entites.DTO.RoleEnum;
import org.example.gestionrh.Entites.DTO.Sexe;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String phone;
    private String keyckoakid;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY)
    private Set<User> subordonnes = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Conge> conges = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SoldeConge> soldesConges = new HashSet<>();

}

