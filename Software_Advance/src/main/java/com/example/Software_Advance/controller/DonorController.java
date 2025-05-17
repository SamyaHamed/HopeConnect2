package com.example.Software_Advance.controller;

import com.example.Software_Advance.models.Tables.Donor;
import com.example.Software_Advance.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDonorById(@PathVariable Long id) {
        Optional<Donor> donor = donorService.getDonorById(id);
        if (donor.isPresent()) {
            return ResponseEntity.ok(donor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No donor found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonorById(@PathVariable Long id) {
        try {
            donorService.deleteDonor(id);
            return ResponseEntity.ok("Donor with ID " + id + " has been deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting donor: " + e.getMessage());
        }
    }
}
