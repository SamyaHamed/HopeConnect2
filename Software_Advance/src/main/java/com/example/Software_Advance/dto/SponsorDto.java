package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.SponsorshipType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class SponsorDto {
    private UserDto user;
    private SponsorshipType sponsorshipType;
    private LocalDate startDate;
    private String status;
    private List<OrphanDto> orphans;

}

