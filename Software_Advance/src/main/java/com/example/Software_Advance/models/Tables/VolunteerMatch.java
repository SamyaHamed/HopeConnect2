/*package com.example.Software_Advance.models.Tables;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_matches")
public class VolunteerMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private HelpRequest request;

    @Column(name = "match_status", nullable = false)
    private Boolean matchStatus;

    @Column(name = "matched_at", nullable = false)
    private LocalDateTime matchedAt;
}*/