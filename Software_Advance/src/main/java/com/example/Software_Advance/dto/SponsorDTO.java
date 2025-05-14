package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.SponsorshipType;
import lombok.Data;

import java.time.LocalDate;

@Data

public class SponsorDTO {
    private UserDTO user;
    private SponsorshipType sponsorshipType;
    private LocalDate startDate;
    private String status;
}

