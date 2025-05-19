package com.example.Software_Advance.controller;

import com.example.Software_Advance.models.Tables.Notification;
import com.example.Software_Advance.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationService emailNotificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Notification notification) {
        emailNotificationService.sendEmailNotification(notification);
        return ResponseEntity.ok("The Email Sent");
    }
}