package com.example.Software_Advance.models.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "org_volunteer")
public class OrgVolunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organization organization;

    @OneToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;
}
