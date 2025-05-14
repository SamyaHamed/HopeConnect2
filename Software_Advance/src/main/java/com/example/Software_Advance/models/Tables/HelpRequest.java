/*package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.HelpRequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "help_requests")
public class HelpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HelpRequestStatus status;
}*/