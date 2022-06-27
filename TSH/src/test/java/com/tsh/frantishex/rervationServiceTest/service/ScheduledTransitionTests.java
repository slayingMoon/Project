package com.tsh.frantishex.rervationServiceTest.service;


import com.tsh.frantishex.reservationService.service.impl.ScheduledTransitionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/reservationServiceProperties/data-reservation.sql")
@TestPropertySource(locations= "classpath:reservationServiceProperties/applicationReservation.properties")
public class ScheduledTransitionTests {
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Test
    public void testFreeSeats(){
      List<Integer> freeSeats= scheduledTransitionService.getFreeSeats(scheduledTransitionService.findEntityById(1L), scheduledTransitionService.findEntityById(3L));
      List<Integer>assertFree= Arrays.asList(41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60);
        Assertions.assertEquals(freeSeats,assertFree);
    }
}
