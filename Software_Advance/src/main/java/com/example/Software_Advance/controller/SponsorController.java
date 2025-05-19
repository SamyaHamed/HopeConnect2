package com.example.Software_Advance.controller;
import com.example.Software_Advance.models.Tables.Sponsor;
import com.example.Software_Advance.services.SponsorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Software_Advance.dto.*;

@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @PostMapping
    public ResponseEntity<SponsorDto> saveSponsor(@Valid @RequestBody Sponsor sponsor) {
        Sponsor savedSponsor = sponsorService.saveSponsor(sponsor);
        SponsorDto sponsorDTO = new SponsorDto();
        sponsorDTO.setSponsorshipType(savedSponsor.getSponsorshipType());
        sponsorDTO.setStartDate(savedSponsor.getStartDate());
        sponsorDTO.setStatus(savedSponsor.getStatus());
        return new ResponseEntity<>(sponsorDTO, HttpStatus.CREATED);
    }

    // Update sponsor
    @PatchMapping("/{id}")
    public ResponseEntity<SponsorDto> updateSponsor(@PathVariable Long id, @Valid @RequestBody Sponsor updatedSponsor) {
        try {
            Sponsor updated = sponsorService.updateSponsor(id, updatedSponsor);
            SponsorDto sponsorDTO = new SponsorDto();
            sponsorDTO.setSponsorshipType(updated.getSponsorshipType());
            sponsorDTO.setStartDate(updated.getStartDate());
            sponsorDTO.setStatus(updated.getStatus());
            return new ResponseEntity<>(sponsorDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}