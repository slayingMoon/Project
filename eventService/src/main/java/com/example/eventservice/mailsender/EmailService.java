package com.example.eventservice.mailsender;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachment(String to, String body, String subject, String attachment) throws MessagingException;
}
