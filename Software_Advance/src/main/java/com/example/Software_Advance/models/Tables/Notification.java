package com.example.Software_Advance.models.Tables;
import com.example.Software_Advance.models.Enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    private ReliefCampaign campaign;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "sent_date", nullable = false, updatable = false)
    private Timestamp sentDate;

    @Column(name = "email_sent", nullable = false)
    private Boolean emailSent = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NotificationStatus status = NotificationStatus.ENQUEUED;

    @PrePersist
    protected void onCreate() {
        this.sentDate = new Timestamp(System.currentTimeMillis());
    }


}
