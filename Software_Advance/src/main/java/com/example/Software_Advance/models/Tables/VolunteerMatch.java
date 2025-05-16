package com.example.Software_Advance.models.Tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "volunteer_matches")
public class VolunteerMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "request_id", nullable = false)
    private HelpRequest request;

    @Column(name = "match_status", nullable = false)
    private Boolean matchStatus;

    @Column(name = "matched_at", nullable = false)
    private LocalDateTime matchedAt;
}