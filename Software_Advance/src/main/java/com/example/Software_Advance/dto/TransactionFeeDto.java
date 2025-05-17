package com.example.Software_Advance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.example.Software_Advance.models.Enums.DonationType;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TransactionFeeDto {
    private Long donationId;
    private Double feePercentage;
    private Double feeAmount;
    private LocalDateTime transactionDate;
    private DonationType donationType;


}
