package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.Availability;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VolunteerDTO {
    private UserDTO user;
    private Long organizationId;
    private String skills;
    private Availability availability;
    private String status;

}

