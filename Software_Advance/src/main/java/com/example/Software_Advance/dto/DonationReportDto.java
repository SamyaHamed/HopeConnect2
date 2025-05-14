package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.DonationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationReportDto {
    private DonationType donationType;
    private Double totalAmount;
}
