package org.example.gestionrh.Entites;
import jakarta.persistence.*;
import lombok.Data;
import org.example.gestionrh.Entites.DTO.RoleEnum;

import java.util.Set;

/*
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;
}

 */
