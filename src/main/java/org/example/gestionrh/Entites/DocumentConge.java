package org.example.gestionrh.Entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DocumentConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFichier;
    private String typeFichier;
    private String url;
    private LocalDate dateUpload;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String base64Content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conge_id")
    private Conge conge;
}
