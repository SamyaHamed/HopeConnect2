/*package com.example.Software_Advance.models.Tables;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "campaign_id", referencedColumnName = "id", nullable = true)
    private ReliefCampaign campaign;

    @Column(name = "email_content", nullable = false, columnDefinition = "TEXT")
    private String emailContent;

    @Column(name = "sent_date", nullable = false, updatable = false)
    private Timestamp sentDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "broadcast", nullable = false)
    private Boolean broadcast = false;

    @Column(name = "email_sent", nullable = false)
    private Boolean emailSent;

}*/