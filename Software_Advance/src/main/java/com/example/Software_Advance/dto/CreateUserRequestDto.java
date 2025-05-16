package com.example.Software_Advance.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private UserDto user;
    private DonorDto donor;
    private SponsorDto sponsor;
    private VolunteerDto volunteer;
    private OrganizationDto organization;
    private OrphanageDto orphanage;

}
