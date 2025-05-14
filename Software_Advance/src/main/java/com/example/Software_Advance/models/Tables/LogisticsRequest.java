/*package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.DonationType;
import com.example.Software_Advance.models.Enums.RequestStatus;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logistics_requests")
public class LogisticsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationType donationType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String deliveryLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
}*/