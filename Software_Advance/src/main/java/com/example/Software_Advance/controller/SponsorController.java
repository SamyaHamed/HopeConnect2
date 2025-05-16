package com.example.Software_Advance.controller;

import com.example.Software_Advance.models.Tables.Sponsor;
import com.example.Software_Advance.services.SponsorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @PostMapping
    public ResponseEntity<Sponsor> saveSponsor(@Valid @RequestBody Sponsor sponsor) {
        Sponsor savedSponsor = sponsorService.saveSponsor(sponsor);
        return new ResponseEntity<>(savedSponsor, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable Long id, @Valid @RequestBody Sponsor updatedSponsor) {
        try {
            Sponsor updated = sponsorService.updateSponsor(id, updatedSponsor);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}