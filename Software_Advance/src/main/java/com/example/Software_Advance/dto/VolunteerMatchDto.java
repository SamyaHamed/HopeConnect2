package com.example.Software_Advance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data

public class VolunteerMatchDto {
    private Long id;
    private Long volunteerId;
    private Long requestId;
    private Boolean matchStatus;
    private LocalDateTime matchedAt;
}
