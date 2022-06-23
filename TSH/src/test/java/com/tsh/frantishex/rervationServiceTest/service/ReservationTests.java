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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/reservationServiceProperties/data-reservation.sql")
@TestPropertySource(locations= "classpath:reservationServiceProperties/applicationReservation.properties")
public class ReservationTests {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private SeatServiceImpl seatService;


    @Test
    public void testTripCreationTest() {
        Seat[] s = new Seat[40];
        List<Seat> seats = new LinkedList<>();
        for (int i = 0; i < 40; i++) {
            s[i] = new Seat(i + 1);
            seats.add(s[i]);

        }

        List<ScheduledTransition> scheduledTransitionList =
                Arrays.asList(new ScheduledTransition(LocalDateTime.now().plusHours(2), new ReservationCity(Country.BULGARIA, "Sofia"), seats),
                        new ScheduledTransition(LocalDateTime.now().plusHours(3), new ReservationCity(Country.BULGARIA, "Blagoevgrad"), seats.subList(15, 20)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(4), new ReservationCity(Country.BULGARIA, "Sandanski"), seats.subList(20, 25)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(5), new ReservationCity(Country.GREECE, "Solun"), seats.subList(25, 30)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(8), new ReservationCity(Country.GREECE, "Atina"), seats.subList(30, 40)));

        List<Driver> drivers = new LinkedList<>(Arrays.asList(new Driver("Shofior", "0895433211", "0891235665"),
                new Driver("Shofior2", "0895430011", "0891200665"),
                new Driver("Djivko", "0895430019", "0891200669"),
                new Driver("Zdravko", "0895430111", "0891200165")));
        ScheduledTrip trip = new ScheduledTrip(new Bus(60, drivers), scheduledTransitionList);
        scheduledTripService.createOrUpdateEntity(trip);


    }

    @Test
    public void reservationCreationTest() {
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);

       Reservation reservation= reservationService.reserve(new Reservation.ReservationBuilder()
                            .from(scheduledTrip.getScheduledTransitions().get(3))
                            .to( scheduledTrip.getScheduledTransitions().get(4))
                            .seat(seatService.findEntityById(31L))
                            .passenger(new Passenger("Anna-maria", "Petrova", "Kartselska", 19 + 1, "1896437439", "an7i53e@abv.bfg"))
                            .reservationStatus(ReservationStatus.NEW)
                            .reservationDate(LocalDateTime.now())
                    .build());
       // Assertions.assertNotNull(reservation.getId());
    }
    @Test
    public void setStatusDeletedTest(){

        Reservation reservation = reservationService.findEntityById(8L);
        reservationService.setStatusDeleted(reservation);
    }

}
