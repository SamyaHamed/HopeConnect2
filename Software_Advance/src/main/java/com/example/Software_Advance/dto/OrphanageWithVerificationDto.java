package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.verificationStatus;
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
    private verificationStatus status;

    public OrphanageWithVerificationDto(Orphanage orphanage, verificationStatus status) {
        this.orphanageId = orphanage.getId();
        this.status = status;
    }

}
