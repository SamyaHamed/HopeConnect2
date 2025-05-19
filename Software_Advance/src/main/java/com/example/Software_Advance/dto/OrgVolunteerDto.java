package com.example.Software_Advance.dto;
import com.example.Software_Advance.models.Enums.ParticipationStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class OrgVolunteerDto {
    private Long volunteerId;
    private Long organizationId;
    private String skills;
    private ParticipationStatus participationStatus;

}
