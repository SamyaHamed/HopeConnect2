package com.example.Software_Advance.externalApi;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String defaultFrom;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        // نستخدم الـ username الفعلي من JavaMailSenderImpl
        if (mailSender instanceof JavaMailSenderImpl) {
            this.defaultFrom = ((JavaMailSenderImpl) mailSender).getUsername();
        } else {
            this.defaultFrom = "no-reply@example.com";
        }
    }

    /** إرسال نص عادي */
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(defaultFrom);   // هنا من قابل
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
    }

    /** إرسال HTML */
    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
        helper.setFrom(defaultFrom); // من هنا أيضاً
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        mailSender.send(message);
    }
}
