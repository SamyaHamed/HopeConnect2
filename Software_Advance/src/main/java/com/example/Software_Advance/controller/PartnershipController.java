package com.example.Software_Advance.controller;

import com.example.Software_Advance.dto.PartnershipDto;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Tables.Partnership;
import com.example.Software_Advance.services.PartnershipService;
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
    public ResponseEntity<String> deleteReview(@PathVariable Long partnershipId) {
        partnershipService.deletePartnership(partnershipId);
        return ResponseEntity.ok("Partnership deleted successfully!");
    }

    // 1. Get all partnerships by organization ID
    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<Partnership>> getPartnershipsByOrganization(@PathVariable Long orgId) {
        return ResponseEntity.ok(partnershipService.getPartnershipsByOrganizationId(orgId));
    }

    // 2. Get partnerships by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Partnership>> getPartnershipsByType(@PathVariable PartnershipType type) {
        return ResponseEntity.ok(partnershipService.getPartnershipByType(type));
    }

    // 3. Get partnerships by organization ID and status
    @GetMapping("/organization/{orgId}/status/{status}")
    public ResponseEntity<List<Partnership>> getByOrgAndStatus(@PathVariable Long orgId,
                                                               @PathVariable PartnershipStatus status) {
        return ResponseEntity.ok(partnershipService.getByOrganizationAndStatus(orgId, status));
    }

    // 4. Get all partnerships sorted by agreement date
    @GetMapping("/sorted/agreement-date")
    public ResponseEntity<List<Partnership>> getAllSortedByAgreementDate() {
        return ResponseEntity.ok(partnershipService.getAllSortedByAgreementDate());
    }


}
