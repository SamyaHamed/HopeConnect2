package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.ParticipationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.GroupSequence;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "org_volunteer")
public class OrgVolunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    @JsonIgnoreProperties("orgVolunteers")
    private Volunteer volunteer;


    @Column(name = "skills", nullable = false)
    private String skills;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipationStatus participationStatus;

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


}