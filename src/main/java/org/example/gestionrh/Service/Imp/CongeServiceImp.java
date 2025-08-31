package org.example.gestionrh.Service.Imp;


import org.example.gestionrh.Entites.Conge;
import org.example.gestionrh.Entites.DTO.CongeCreateDTO;
import org.example.gestionrh.Entites.DTO.CongeDTO;
import org.example.gestionrh.Entites.DTO.Statut;
import org.example.gestionrh.Mapper.CongeMapper;
import org.example.gestionrh.Repository.CongeRepository;
import org.example.gestionrh.Service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CongeServiceImp implements CongeService {

    @Autowired
    private CongeRepository repo;

    @Autowired
    private CongeMapper mapper;
    @Autowired
    private CongeRepository congeRepository;

    @Override
    public CongeDTO createConge(CongeCreateDTO congeCreateDTO) {
        Conge entity = mapper.toEntity(congeCreateDTO);
        Conge saved = repo.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public CongeDTO updateConge(CongeDTO congeDTO) {
        Conge entity = repo.findById(congeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Congé introuvable"));
        mapper.updateEntityFromDTO(congeDTO, entity);
        Conge updated = repo.save(entity);
        return mapper.toDTO(updated);
    }

    @Override
    public boolean deleteConge(long congeID) {
        if (!repo.existsById(congeID)) {
            return false;
        }
        repo.deleteById(congeID);
        return true;
    }

    @Override
    public Page<CongeDTO> getAllConges(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public Page<CongeDTO> getAllCongesByEmploye(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByUser_Email(email, pageable).map(mapper::toDTO);
    }

    @Override
    public boolean updateStatus(long congeID, String status) {
        Conge conge= repo.findById(congeID).orElse(null);

        if(conge!=null){
            conge.setStatut(Statut.valueOf(status));
            repo.save(conge);
            return true;
        }
        return false;
    }
}

