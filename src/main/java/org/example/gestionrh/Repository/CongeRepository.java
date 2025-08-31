package org.example.gestionrh.Repository;

import org.example.gestionrh.Entites.Conge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeRepository extends JpaRepository<Conge, Long> {

    Page<Conge> findByUser_Id(Long userId, Pageable pageable);
    Page<Conge> findByUser_Email(String email, Pageable pageable);
}
