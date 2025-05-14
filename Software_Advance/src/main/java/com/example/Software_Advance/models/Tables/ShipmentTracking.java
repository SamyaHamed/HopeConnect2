
/*
package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.ShipmentStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipment_tracking")
public class ShipmentTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "logistics_request_id", nullable = false)
    private LogisticsRequest logisticsRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShipmentStatus status;

    @Column(name = "current_location", nullable = false, length = 255)
    private String currentLocation;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}*/