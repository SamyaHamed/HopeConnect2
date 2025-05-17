
package com.example.Software_Advance.models.Tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import com.example.Software_Advance.models.Enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "donation")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Donation type is required")
    @Column(name = "donation_type", nullable = false)
    private DonationType donationType;

    @Column(name = "organization_id", nullable = true)
    private Long organizationId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type is required")
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Column(name = "donation_amount", nullable = true)
    private Double donationAmount;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    @JsonBackReference
    private Donor donor;
}
