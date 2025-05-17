package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class DonationDto {
    private DonationType donationType;
    private Long organizationId;
    private PaymentType paymentType;
    private Double donationAmount;
    public DonationDto(DonationType donationType, Double donationAmount) {
        this.donationType = donationType;
        this.donationAmount = donationAmount;
    }
    public DonationDto(DonationType donationType, Double donationAmount, Long organizationId, PaymentType paymentType) {
        this.donationType = donationType;
        this.donationAmount = donationAmount;
        this.organizationId = organizationId;
        this.paymentType = paymentType;
    }

