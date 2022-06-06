package com.example.eventservice;

import com.example.eventservice.event.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class InvokeService {

    @Autowired
    private EventManager eventManager;

    @PostConstruct
    void init() {
        TestEvent test = new TestEvent("Rumka i Mancho");
        eventManager.sendEvent(test);
    }
}
