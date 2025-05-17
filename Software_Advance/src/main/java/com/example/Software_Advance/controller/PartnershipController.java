package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.PartnershipDto;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Tables.Partnership;
import com.example.Software_Advance.services.PartnershipService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Partnership")
public class PartnershipController {

    @Autowired
    PartnershipService partnershipService;

    @PostMapping("/create")
    public ResponseEntity<String> createPartnership(@RequestBody PartnershipDto request){
        Partnership partnership = partnershipService.addPartnership(request , request.getOrganizationId());
        return ResponseEntity.ok("Partnership added successfully!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<PartnershipDto> updatePartnership(@PathVariable Long id,
                                                            @RequestBody PartnershipDto request) {
        Partnership updated = partnershipService.updatePartnership(id, request);

        PartnershipDto responseDTO = new PartnershipDto(
                updated.getEmail(),
                updated.getPhone(),
                updated.getPartnershipType(),
                updated.getAgreementDate(),
                updated.getEndDate(),
                updated.getStatus(),
                updated.getOrganization().getId()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartnership(@PathVariable Long id) {
        partnershipService.deletePartnership(id);
        return ResponseEntity.ok("Partnership deleted successfully!");
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Partnership>> getPartnershipsByType(@PathVariable PartnershipType type) {
        return ResponseEntity.ok(partnershipService.getPartnershipByType(type));
    }

    @GetMapping("/organization/{orgId}/status/{status}")
    public ResponseEntity<List<Partnership>> getByOrgAndStatus(@PathVariable Long orgId,
                                                               @PathVariable PartnershipStatus status) {
        return ResponseEntity.ok(partnershipService.getByOrganizationAndStatus(orgId, status));
    }

    @GetMapping("/sorted/agreement-date")
    public ResponseEntity<List<Partnership>> getAllSortedByAgreementDate() {
        return ResponseEntity.ok(partnershipService.getAllSortedByAgreementDate());
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<Partnership>> getPartnershipsByOrganizationId(@PathVariable Long organizationId) {
        List<Partnership> partnerships = partnershipService.getPartnershipsByOrganizationId(organizationId);
        return ResponseEntity.ok(partnerships);
    }

    // ======= Exception Handlers =======

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
    }
}
