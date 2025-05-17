package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HelpRequestDto {
    private String title;
    private HelpRequestStatus status;

    private String description;
    private Long orphanageId;
}
