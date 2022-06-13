package com.example.eventservice.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
