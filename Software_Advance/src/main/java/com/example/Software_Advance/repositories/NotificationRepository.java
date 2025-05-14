package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Notification;
import com.example.Software_Advance.models.Enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);
    List<Notification> findByBroadcastTrue();
    List<Notification> findByStatus(NotificationStatus status);
}
