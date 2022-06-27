package com.tsh.frantishex.rervationServiceTest.service;


import com.tsh.frantishex.reservationService.model.entity.*;
import com.tsh.frantishex.reservationService.service.impl.ScheduledTripServiceImpl;
import com.tsh.frantishex.tripService.domain.entity.Transition;
import com.tsh.frantishex.tripService.domain.entity.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedualedTripCreationTest {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    @Test
    @Transactional
    @Rollback
    public void tripCreation() {
        Transsition t1 = new Transsition(new ReservationCity("kurevo"), 0);
        Transsition t2 = new Transsition(new ReservationCity("burevo"), 1);
        Transsition t3 = new Transsition(new ReservationCity("shundevo"), 2);

        List<Transsition> transitions = Arrays.asList(t1, t2, t3);
        Tripp trip = new Tripp();
        trip.setDepartureTime(LocalTime.now());
        trip.setTransitions(transitions);
        List<Driver> drivers = Collections.singletonList(new Driver("Onan", "0895431019", "0891100669"));
        ScheduledTrip scheduledTrip = scheduledTripService.createScheduledTripFromTrip(trip, LocalDate.now(), drivers);
        System.out.println(scheduledTrip);

    }
}
