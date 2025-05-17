package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.EducationStatus;
import com.example.Software_Advance.models.Enums.HealthCondition;
import com.example.Software_Advance.models.Tables.Orphan;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class OrphanDto {
    private  String name ;
    private  int age ;
    private EducationStatus educationStatus;
    private HealthCondition healthCondition ;
    private Long orphanageId;
   private String orphanageName;//output
    private Long sponsorId;
 private String sponsorName;//output


    public OrphanDto(Orphan orphan) {
        this.name = orphan.getName();
        this.age = orphan.getAge();
        this.educationStatus = orphan.getEducationStatus();
        this.healthCondition = orphan.getHealthCondition();

        if (orphan.getOrphanage() != null) {
            this.orphanageId = orphan.getOrphanage().getId();
            this.orphanageName = orphan.getOrphanage().getUser().getName();
        }

        if (orphan.getSponsor() != null) {
            this.sponsorId = orphan.getSponsor().getId();
            this.sponsorName = orphan.getSponsor().getUser().getName();
        }
    }

}
