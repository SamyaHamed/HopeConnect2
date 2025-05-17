package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Enums.RequestStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Data
public class LogisticsRequestDto {
    private Long donorId;
    private Long orphanageId;
    private DonationType donationType;
    private Integer quantity;
    private String pickupLocation;
    private String deliveryLocation;
    private RequestStatus status;

}