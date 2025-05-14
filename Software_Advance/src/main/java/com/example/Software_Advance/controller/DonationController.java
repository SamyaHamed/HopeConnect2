package com.example.Software_Advance.controller;
import com.example.Software_Advance.dto.DonationDTO;
import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Tables.Donation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Software_Advance.services.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    @Autowired
    private DonationService donationService;
    @Autowired
    private DonorService donorService;

    @GetMapping("/{donorId}")
    public ResponseEntity<List<DonationDTO>> getDonationsByDonorId(@PathVariable Long donorId) {
        List<DonationDTO> donationDTOs = donationService.getDonationByDonorId(donorId);

        if (donationDTOs.isEmpty()) {
            //return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(donationDTOs);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<DonationDTO>> filterDonations(
            @RequestParam(required = false) DonationType donationType,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {

        List<DonationDTO> filteredDonations = donationService.filterDonations(donationType, minAmount, maxAmount);
        return ResponseEntity.ok(filteredDonations);
    }


    @PutMapping("/{donationId}") //http://localhost:8080/api/donations/4?donorId=4    example
    public ResponseEntity<String> updateDonation(@PathVariable Long donationId,
                                                 @RequestParam Long donorId,
                                                 @RequestBody DonationDTO dto) {
        try {
            donationService.updateDonation(donationId, donorId, dto);
            return ResponseEntity.ok("Donation updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/donate")
    public ResponseEntity<?> createDonation(@RequestBody DonationDTO dto,
                                            @RequestParam Long donorId) {
        try {
            Donation donation = donationService.saveDonation(dto, donorId);
            return ResponseEntity.ok(donation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{donationId}")
    public ResponseEntity<String> deleteDonation(@PathVariable Long donationId) {
        try {
            donationService.deleteDonation(donationId);
            return ResponseEntity.ok("Donation deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/totalAmount/{donorId}")
    public ResponseEntity<String> calculateAmount(@PathVariable Long donorId) {
        try {
            Double totalAmount = donationService.calculateTotalDonations(donorId);

            if (totalAmount == null || totalAmount == 0.0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No donations found for donor with ID " + donorId);
            }

            return ResponseEntity.ok("The total donations for donor with ID " + donorId + " is: " + totalAmount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while calculating the total donations.");
        }
    }

}