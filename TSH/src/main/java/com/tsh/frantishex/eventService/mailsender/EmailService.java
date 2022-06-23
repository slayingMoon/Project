package com.tsh.frantishex.eventService.mailsender;


import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String toEmail, String body, String subject);
    void sendMessageWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException;
}
