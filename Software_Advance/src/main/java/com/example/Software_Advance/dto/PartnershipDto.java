package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.PartnershipType;
import com.example.Software_Advance.models.Enums.PartnershipStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartnershipDto {

    private Long organizationId;
    private String email;
    private String phone;

    private PartnershipType partnershipType;
    private PartnershipStatus status;

    private LocalDate agreementDate;
    private LocalDate endDate;
    public PartnershipDto(String email, String phone, PartnershipType partnershipType,
                          LocalDate agreementDate, LocalDate endDate,
                          PartnershipStatus status, Long organizationId) {
        this.email = email;
        this.phone = phone;
        this.partnershipType = partnershipType;
        this.agreementDate = agreementDate;
        this.endDate = endDate;
        this.status = status;
        this.organizationId = organizationId;
    }


}