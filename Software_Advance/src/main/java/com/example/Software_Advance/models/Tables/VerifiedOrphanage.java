/*package com.example.Software_Advance.models.Tables;
import com.example.Software_Advance.models.Enums.*;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "verified_orphanages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifiedOrphanage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;


    @Column(name = "verified_by_admin_id", nullable = false)
    private Long verifiedByAdmin;

    @NotNull
    @Column(name = "verification_date", nullable = false)
    private LocalDate verificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private VerificationStatus status;
}*/