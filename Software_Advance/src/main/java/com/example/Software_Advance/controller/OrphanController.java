package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.OrphanDto;
import com.example.Software_Advance.exceptions.DuplicateOrphanException;
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
    public ResponseEntity<?> createOrphan(@RequestBody @Valid OrphanDto orphanDTO) {
        try {
            Orphan created = orphanService.createOrphan(orphanDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (DuplicateOrphanException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Duplicate orphan: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating orphan: " + e.getMessage());
        }
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getOrphanById(@PathVariable Long id) {
        try {
            Optional<OrphanDto> orphan = orphanService.getOrphanById(id);
            if (orphan.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orphan found with ID " + id);
            } else {
                return ResponseEntity.ok(orphan.get());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving orphan: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrphans() {
        try {
            List<OrphanDto> orphans = orphanService.getAllOrphans();
            if (orphans.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orphans found in the database.");
            }
            return ResponseEntity.ok(orphans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving orphans: " + e.getMessage());
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> searchOrphansByName(@PathVariable String name) {
        try {
            List<OrphanDto> orphans = orphanService.searchOrphansByName(name);
            if (orphans.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("There are no Orphans with this Name: " + name + ".");
            }
            return ResponseEntity.ok(orphans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error searching orphans by name: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrphan(@PathVariable Long id, @RequestBody OrphanDto orphanDTO) {
        try {
            Orphan updated = orphanService.updateOrphan(id, orphanDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating orphan: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrphan(@PathVariable Long id) {
        try {
            orphanService.deleteOrphan(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting orphan: " + e.getMessage());
        }
    }
}
