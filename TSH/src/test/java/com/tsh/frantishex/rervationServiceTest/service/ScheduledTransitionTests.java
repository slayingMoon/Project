package com.tsh.frantishex.rervationServiceTest.service;


import com.tsh.frantishex.reservationService.service.impl.ScheduledTransitionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/reservationServiceProperties/data-reservation.sql")
@TestPropertySource(locations= "classpath:reservationServiceProperties/applicationReservation.properties")
public class ScheduledTransitionTests {
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Test
    public void testFreeSeats(){
        System.out.println(scheduledTransitionService.getFreeSeats(scheduledTransitionService.findEntityById(2L), scheduledTransitionService.findEntityById(4L)));
    }
}
