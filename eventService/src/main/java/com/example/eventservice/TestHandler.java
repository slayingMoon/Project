package com.example.eventservice;

import com.example.eventservice.event.annotation.EventHandler;
import com.example.eventservice.mailsender.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class TestHandler {

    @Autowired
    private EmailServiceImpl service;

    @EventHandler
    public void TestEvent(TestEvent testEvent) {
        System.out.println(testEvent.getTest());
    }

    @EventHandler
    public void TestEvent2(TestEvent testEvent) {
        System.out.println(testEvent.getTest() + " chushkopek");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void testEvent3() {
        service.sendSimpleMessage(
                "ourkurisrock@gmail.com",
                "test",
                "test");
    }

    //Test With Attachments WORKING!
    //Commented to stop throwing "File Not Found" Exceptions

    /*@EventListener(ApplicationReadyEvent.class)
    public void testEvent4() throws MessagingException {
        service.sendMessageWithAttachment("ourkurisrock@gmail.com",
                "test attachment",
                "test attachment",
                "C:\\Users\\Niki Kaloyanov\\Desktop\\01_block_diagram.gif");
    }*/

}
