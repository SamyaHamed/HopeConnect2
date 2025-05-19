package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.SponsorshipType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class SponsorDto {
    private UserDto user;
    private SponsorshipType sponsorshipType;
    private LocalDate startDate;
    private String status;
}


