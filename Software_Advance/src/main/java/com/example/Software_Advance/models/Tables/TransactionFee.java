/*package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.DonationType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class TransactionFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donation_id", nullable = false)
    private Donor donation;

    @Column(nullable = false)
    private Double feePercentage;

    @Column(nullable = false)
    private Double feeAmount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "donation_type", nullable = false)
    private DonationType donationType;

}*/