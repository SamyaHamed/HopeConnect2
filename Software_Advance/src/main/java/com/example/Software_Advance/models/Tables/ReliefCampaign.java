package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.CampaignStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter @Getter
@Table(name = "relief_campaigns")
public class ReliefCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organization organisation;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double targetAmount;

    @Column(nullable = false)
    private Double collectedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignStatus status;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(nullable = false)
    private Boolean deliveredToAllDonors = false;


}