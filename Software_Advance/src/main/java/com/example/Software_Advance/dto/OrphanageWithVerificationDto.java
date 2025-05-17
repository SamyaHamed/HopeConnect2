package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.VerificationStatus;
import com.example.Software_Advance.models.Tables.Orphanage;
import lombok.*;
@Getter @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrphanageWithVerificationDto {

    private Long orphanageId;
    private Long adminId;
    private VerificationStatus status;

    public OrphanageWithVerificationDto(Orphanage orphanage, VerificationStatus status) {
        this.orphanageId = orphanage.getId();
        this.status = status;
    }

}
