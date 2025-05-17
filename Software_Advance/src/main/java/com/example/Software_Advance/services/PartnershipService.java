package com.example.Software_Advance.services;

import com.example.Software_Advance.exceptions.BadRequestException;
import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Tables.Organization;
import com.example.Software_Advance.models.Tables.Partnership;
import com.example.Software_Advance.repositories.OrganizationRepository;
import com.example.Software_Advance.repositories.PartnershipRepository;
import com.example.Software_Advance.dto.PartnershipDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + organizationID));

        List<Partnership> existing = partnershipRepository.findByEmail(partnershipDTO.getEmail());
        boolean duplicateExists = existing.stream()
                .anyMatch(p -> p.getOrganization().getId().equals(organizationID));

        if (duplicateExists) {
            throw new BadRequestException("Partnership with this email already exists for the organization");
        }

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
    public List<Partnership> getPartnershipsByOrganizationId(Long organizationId) {
        // تحقق أولاً من وجود الـ Organization
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + organizationId));

        // الآن ابحث عن الـ Partnerships
        return partnershipRepository.findByOrganizationId(organizationId);
    }

    public Partnership updatePartnership(Long partnershipId, PartnershipDto partnershipDTO) {
        Partnership partnership = partnershipRepository.findById(partnershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Partnership not found with ID: " + partnershipId));

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
            LocalDate agreementDate = partnershipDTO.getAgreementDate() != null
                    ? partnershipDTO.getAgreementDate()
                    : partnership.getAgreementDate();

            if (partnershipDTO.getEndDate().isBefore(agreementDate)) {
                throw new BadRequestException("End date cannot be before agreement date");
            }

            partnership.setEndDate(partnershipDTO.getEndDate());
        }

        partnership.setModifiedAt(LocalDateTime.now());

        return partnershipRepository.save(partnership);
    }


    public void deletePartnership(Long partnershipId) {
        if (!partnershipRepository.existsById(partnershipId)) {
            throw new ResourceNotFoundException("Partnership with ID " + partnershipId + " not found");
        }
        partnershipRepository.deleteById(partnershipId);
    }


    public List<Partnership> getPartnershipByType(PartnershipType type) {
        return partnershipRepository.findByPartnershipType(type);
    }

    public List<Partnership> getByOrganizationAndStatus(Long orgId, PartnershipStatus status) {
        return partnershipRepository.findByOrganizationIdAndStatus(orgId, status);
    }

    public List<Partnership> getAllSortedByAgreementDate() {
        return partnershipRepository.findAllByOrderByAgreementDateAsc();
    }
}
