package org.example.gestionrh.Service;

import org.example.gestionrh.Entites.DTO.CongeCreateDTO;
import org.example.gestionrh.Entites.DTO.CongeDTO;
import org.springframework.data.domain.Page;

public interface CongeService {
    CongeDTO createConge(CongeCreateDTO congeCreateDTO);
    CongeDTO updateConge(CongeDTO congeDTO);
    boolean deleteConge(long congeID);
    Page<CongeDTO> getAllConges(int page, int size);
    Page<CongeDTO> getAllCongesByEmploye(String email,int page, int size);
    boolean updateStatus(long congeID, String status);
}
