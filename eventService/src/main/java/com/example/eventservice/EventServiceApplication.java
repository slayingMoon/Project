package com.example.eventservice;

import com.example.eventservice.emailsender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class EventServiceApplication {

    @Autowired
    private EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        service.sendSimpleEmail("ourkurisrock@gmail.com",
                "This is Email Body with Attachment...",
                "This email has attachment");
    }

}
