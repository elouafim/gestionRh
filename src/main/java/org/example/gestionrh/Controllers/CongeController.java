package org.example.gestionrh.Controllers;

import org.example.gestionrh.Entites.DTO.CongeCreateDTO;
import org.example.gestionrh.Entites.DTO.CongeDTO;
import org.example.gestionrh.Service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conges")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @PostMapping
    public ResponseEntity<CongeDTO> create(@RequestBody CongeCreateDTO dto) {
        try {

            return ResponseEntity.ok(congeService.createConge(dto));

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CongeDTO> update(@RequestBody CongeDTO dto) {
        return ResponseEntity.ok(congeService.updateConge(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = congeService.deleteConge(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<CongeDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(congeService.getAllConges(page, size));
    }

    @GetMapping("/employe/{email}")
    public ResponseEntity<Page<CongeDTO>> getAllByEmploye(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(congeService.getAllCongesByEmploye(email, page, size));
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<?> updateStatusConge(@PathVariable Long id,@RequestBody String status) {

        try {
            return ResponseEntity.ok(congeService.updateStatus(id,status));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }


    }
}

