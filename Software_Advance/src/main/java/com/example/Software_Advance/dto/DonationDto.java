package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DonationDto {

    @NotNull(message = "Donation type is required")
    private DonationType donationType;
   // private Long campaignId;

    private Long organizationId;

    private PaymentType paymentType;

    @NotNull(message = "Donation amount is required")
    @Positive(message = "Donation amount must be greater than 0")
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
}
