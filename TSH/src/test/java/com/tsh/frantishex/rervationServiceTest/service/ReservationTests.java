package com.tsh.frantishex.rervationServiceTest.service;

import com.tsh.frantishex.reservationService.model.entity.*;
import com.tsh.frantishex.reservationService.model.enums.Country;
import com.tsh.frantishex.reservationService.model.enums.ReservationStatus;
import com.tsh.frantishex.reservationService.service.impl.ReservationServiceImpl;
import com.tsh.frantishex.reservationService.service.impl.ScheduledTripServiceImpl;
import com.tsh.frantishex.reservationService.service.impl.SeatServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/reservationServiceProperties/data-reservation.sql")
@TestPropertySource(locations= "classpath:reservationServiceProperties/applicationReservation.properties")
public class ReservationTests {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private SeatServiceImpl seatService;


    @Test
    @Transactional
    @Rollback
    public void reservationCreationTest() {
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);

       Reservation reservation= reservationService.reserve(new Reservation.ReservationBuilder()
                            .from(scheduledTrip.getScheduledTransitions().get(3))
                            .to( scheduledTrip.getScheduledTransitions().get(4))
                            .seat(seatService.findEntityById(40L))
                            .passenger(new Passenger("Anna-maria", "Petrova", "Kartselska", 19 + 1, "1896437430", "anmfskdk@abv.bfg"))
                            .reservationStatus(ReservationStatus.NEW)
                            .reservationDate(LocalDateTime.now())
                    .build());
        Assertions.assertNotNull(reservation.getId());
    }
    @Test
    @Transactional
    @Rollback
    public void setStatusDeletedTest(){

        Reservation reservation = reservationService.findEntityById(10L);
        reservationService.setStatusDeleted(reservation);
        Assertions.assertEquals(reservation.getReservationStatus(),ReservationStatus.DELETED);
    }

}
