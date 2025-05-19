package com.example.Software_Advance.dto;

import com.example.Software_Advance.models.Enums.ShipmentStatus;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentTrackingUpdateDto {
    private ShipmentStatus status;
    private String currentLocation;

}

