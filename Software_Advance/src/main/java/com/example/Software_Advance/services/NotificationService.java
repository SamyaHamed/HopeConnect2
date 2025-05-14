package com.example.Software_Advance.services;
import com.example.Software_Advance.models.Tables.Notification;
import com.example.Software_Advance.models.Enums.NotificationStatus;
import com.example.Software_Advance.models.Tables.ReliefCampaign;
import com.example.Software_Advance.models.Tables.User;
import com.example.Software_Advance.repositories.NotificationRepository;

import com.example.Software_Advance.repositories.ReliefCampaignRepository;
import com.example.Software_Advance.repositories.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ReliefCampaignRepository reliefRepository;



    public void sendEmailNotification(Notification notification) {
        try {

          //  System.out.println("Campaign from Notification: " + notification.getCampaign());

          User user = userRepository.findById(notification.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + notification.getUser().getId()));

          ReliefCampaign  reliefCampaign = reliefRepository.findById(notification.getCampaign().getId())
                    .orElseThrow(() -> new RuntimeException("Campaign not found with ID: " + notification.getCampaign().getId()));

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject(notification.getSubject());
            helper.setText(reliefCampaign.getDescription(), true);

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

