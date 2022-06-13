package com.example.eventservice;

import com.example.eventservice.mailsender.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

@SpringBootApplication
public class EventServiceApplication {

    @Autowired
    private EmailServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        service.sendSimpleEmail(
                "ourkurisrock@gmail.com",
                "test",
                "test");
    }
}
