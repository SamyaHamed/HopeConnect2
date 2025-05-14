package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.SupportType;
import jakarta.persistence.*;

@Entity
@Table(name = "orphanage_organization")
public class OrphanageOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    @OneToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupportType supportType;
}
