package org.example.gestionrh.Entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SoldeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int annee;
    private int totalConges;
    private int congesPris;
    private int congesRestants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

