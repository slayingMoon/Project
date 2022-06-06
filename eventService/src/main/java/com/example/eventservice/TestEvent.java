package com.example.eventservice;

import com.example.eventservice.event.annotation.EventClass;

@EventClass
public class TestEvent {
    private String test;

    public TestEvent(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
