/*package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Enums.NotificationStatus;
import com.example.Software_Advance.models.Tables.Notification;
import com.example.Software_Advance.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        notification.setStatus(NotificationStatus.ENQUEUED);
        notification.setEmailSent(false);
        return notificationRepository.save(notification);
    }

    @Transactional
    public void updateNotificationStatus(Long notificationId, NotificationStatus newStatus) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setStatus(newStatus);
            if (newStatus == NotificationStatus.SENT) {
                notification.setEmailSent(true);
            }
            notificationRepository.save(notification);
        } else {
            throw new RuntimeException("Notification not found with id: " + notificationId);
        }
    }

    public List<Notification> getNotificationsByStatus(NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getBroadcastNotifications() {
        return notificationRepository.findByBroadcastTrue();
    }

    public void sendNotification(Long notificationId) {
        updateNotificationStatus(notificationId, NotificationStatus.SENT);
        System.out.println("Notification with ID " + notificationId + " sent.");
    }
}*/

package com.example.Software_Advance.services;

import com.example.Software_Advance.models.Tables.Notification;
import com.example.Software_Advance.models.Enums.NotificationStatus;
import com.example.Software_Advance.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    public void sendEmailNotification(Notification notification) {
        try {
            // إعداد الإيميل
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(notification.getUser().getEmail());
            helper.setSubject(notification.getSubject());
            helper.setText(notification.getEmailContent(), true);


            mailSender.send(message);

            notification.setEmailSent(true);
            notification.setStatus(NotificationStatus.SENT);
        } catch (MessagingException e) {

            notification.setEmailSent(false);
            notification.setStatus(NotificationStatus.ERROR);
        }

        notificationRepository.save(notification);
    }
}

