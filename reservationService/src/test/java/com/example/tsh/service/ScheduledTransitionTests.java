package com.example.tsh.service;

import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledTransitionTests {
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Test
    public void testFreeSeats() {
        List<ScheduledTransition> scheduledTransitions = scheduledTransitionService.filteredFromTo(scheduledTransitionService.findEntityById(3L), scheduledTransitionService.findEntityById(5L));
        System.out.println(scheduledTransitionService.getFreeSeats(scheduledTransitions, 40));
    }
}
