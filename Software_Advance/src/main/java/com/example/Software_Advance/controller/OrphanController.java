package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.OrphanDto;
import com.example.Software_Advance.models.Tables.Orphan;
import com.example.Software_Advance.services.OrphanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orphan")
@RequiredArgsConstructor
public class OrphanController {

    private final OrphanService orphanService;

    @PostMapping("/create")
    public ResponseEntity<Orphan> createOrphan(@RequestBody @Valid OrphanDto orphanDTO) {
        Orphan created = orphanService.createOrphan(orphanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getOrphanById(@PathVariable Long id) {
        Optional<OrphanDto> orphan = orphanService.getOrphanById(id);
        if (orphan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orphan found with ID " + id);
        } else {
            return ResponseEntity.ok(orphan.get());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllOrphans() {
        List<OrphanDto>orphans = orphanService.getAllOrphans();
        if(orphans.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orphans found in the database.");
        }
        else return ResponseEntity.ok(orphanService.getAllOrphans());
    }


    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> searchOrphansByName(@RequestParam String name) {
        List<OrphanDto> orphans =orphanService.searchOrphansByName(name);
        if(orphans.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no Orphans with this Name"+name+ ".");
        }
        else return ResponseEntity.ok(orphanService.searchOrphansByName(name));
    }


    @PutMapping("/{id}")
    public Orphan updateOrphan(@PathVariable Long id, @RequestBody OrphanDto orphanDTO) {
        return orphanService.updateOrphan(id, orphanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrphan(@PathVariable Long id) {
        orphanService.deleteOrphan(id);
        return ResponseEntity.noContent().build();
    }
}
