package com.tsh.frantishex.eventService;


import com.tsh.frantishex.eventService.event.annotation.EventClass;

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
