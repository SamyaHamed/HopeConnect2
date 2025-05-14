package com.example.Software_Advance.dto;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private UserDTO user;
    private DonorDTO donor;
    private SponsorDTO sponsor;
    private VolunteerDTO volunteer;
    private OrganizationDTO organization;
    private OrphanageDTO orphanage;
}
