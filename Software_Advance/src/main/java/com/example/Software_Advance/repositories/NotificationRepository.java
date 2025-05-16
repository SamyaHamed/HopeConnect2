/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByCampaignId(Long campaignId);

    List<Notification> findByBroadcastTrue();

    List<Notification> findByEmailSentFalse();
}*/