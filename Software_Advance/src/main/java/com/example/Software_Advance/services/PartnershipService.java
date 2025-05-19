package com.example.Software_Advance.services;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.models.Tables.Partnership;
import com.example.Software_Advance.repositories.*;
import com.example.Software_Advance.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartnershipService {
    @Autowired
    PartnershipRepository partnershipRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    public Partnership addPartnership(PartnershipDto partnershipDTO, Long organizationID) {
        Organization organization = organizationRepository.findById(organizationID)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Partnership partnership = Partnership.builder()
                .email(partnershipDTO.getEmail())
                .phone(partnershipDTO.getPhone())
                .partnershipType(partnershipDTO.getPartnershipType())
                .status(partnershipDTO.getStatus())
                .agreementDate(partnershipDTO.getAgreementDate())
                .endDate(partnershipDTO.getEndDate())
                .build();

        partnership.setOrganization(organization);

        return partnershipRepository.save(partnership);
    }

    public Partnership updatePartnership(Long partnershipId, PartnershipDto partnershipDTO) {
        Partnership partnership = partnershipRepository.findById(partnershipId)
                .orElseThrow(() -> new EntityNotFoundException("Partnership not found with ID: " + partnershipId));

        if (partnershipDTO.getEmail() != null) {
            partnership.setEmail(partnershipDTO.getEmail());
        }
        if (partnershipDTO.getPhone() != null) {
            partnership.setPhone(partnershipDTO.getPhone());
        }
        if (partnershipDTO.getPartnershipType() != null) {
            partnership.setPartnershipType(partnershipDTO.getPartnershipType());
        }
        if (partnershipDTO.getAgreementDate() != null) {
            partnership.setAgreementDate(partnershipDTO.getAgreementDate());
        }
        if (partnershipDTO.getEndDate() != null) {
            if (partnershipDTO.getEndDate().isBefore(partnershipDTO.getAgreementDate())) {
                throw new IllegalArgumentException("End date cannot be before agreement date");
            }
            partnership.setEndDate(partnershipDTO.getEndDate());
        }

        partnership.setModifiedAt(LocalDateTime.now());

        return partnershipRepository.save(partnership);
    }

    public void deletePartnership(Long partnershipId) {
        if (!partnershipRepository.existsById(partnershipId)) {
            throw new EntityNotFoundException("Partnership with ID " + partnershipId + " not found");
        }
        partnershipRepository.deleteById(partnershipId);
    }


    public List<Partnership> getPartnershipsByOrganizationId(Long orgId) {
        return partnershipRepository.findByOrganizationId(orgId);
    }
    public List<Partnership> getPartnershipByType(PartnershipType type){
        return partnershipRepository.findByPartnershipType(type);
    }
    public List<Partnership> getByOrganizationAndStatus(Long orgId, PartnershipStatus status) {
        return partnershipRepository.findByOrganizationIdAndStatus(orgId, status);
    }
    public List<Partnership> getAllSortedByAgreementDate() {
        return partnershipRepository.findAllByOrderByAgreementDateAsc();
    }
}