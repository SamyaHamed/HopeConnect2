package com.example.Software_Advance.dto;


import com.example.Software_Advance.models.Enums.HealthCondition;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrphanHealthSummaryDto {
    private HealthCondition healthCondition;
    private Long count;
}
