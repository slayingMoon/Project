package com.example.eventservice;

import com.example.eventservice.event.annotation.EventHandler;
import org.springframework.stereotype.Component;

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
