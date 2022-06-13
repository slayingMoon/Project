package com.example.eventservice;

import com.example.eventservice.event.annotation.EventHandler;
import com.example.eventservice.mailsender.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class TestHandler {



    @EventHandler
    public void TestEvent(TestEvent testEvent) {
        System.out.println(testEvent.getTest());
    }

    @EventHandler
    public void TestEvent2(TestEvent testEvent) {
        System.out.println(testEvent.getTest() + " chushkopek");

    }







}
