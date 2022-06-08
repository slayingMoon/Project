package com.example.tsh;

import static com.example.tsh.model.enums.ReservationPaid.PAID;

import com.example.tsh.model.entity.*;
import com.example.tsh.model.enums.Country;
import com.example.tsh.model.enums.ReservationConfirmed;
import com.example.tsh.service.ReservationService;

import com.example.tsh.service.impl.OpenFolderServiceImpl;
import com.example.tsh.service.impl.ReservationServiceImpl;
import com.example.tsh.service.impl.ScheduledTripServiceImpl;
import com.example.tsh.service.impl.SeatServiceImpl;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTests {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private SeatServiceImpl seatService;

    @Autowired
    private OpenFolderServiceImpl openFolderService;

    @Test
    public void testTripCreationTest() {
        Seat[] s = new Seat[40];
        List<Seat> seats = new LinkedList<>();
        for (int i = 0; i < 40; i++) {
            s[i] = new Seat(i + 1);
            seats.add(s[i]);

        }

        List<ScheduledTransition> scheduledTransitionList =
                Arrays.asList(new ScheduledTransition(LocalDateTime.now().plusHours(2), new City(Country.BULGARIA, "Sofia"), seats),
                        new ScheduledTransition(LocalDateTime.now().plusHours(3), new City(Country.BULGARIA, "Blagoevgrad"), seats.subList(15, 20)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(4), new City(Country.BULGARIA, "Sandanski"), seats.subList(20, 25)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(5), new City(Country.GREECE, "Solun"), seats.subList(25, 30)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(8), new City(Country.GREECE, "Atina"), seats.subList(30, 40)));

        List<Driver> drivers = new LinkedList<>(Arrays.asList(new Driver("Shofior", "0895433211", "0891235665"),
                new Driver("Shofior2", "0895430011", "0891200665"),
                new Driver("Djivko", "0895430019", "0891200669"),
                new Driver("Zdravko", "0895430111", "0891200165")));
        ScheduledTrip trip = new ScheduledTrip(new Bus(60, drivers), scheduledTransitionList);
        scheduledTripService.createOrUpdateEntity(trip);

    }

    // ReservationServiceModel r = new ReservationServiceModel(fr, t, scheduledTrip,seatServiceModel,"Has", "Mokarov", PAID, CONFIRMED, LocalDateTime.now(), ReservationDirections.ONE_WAY);
    //reservationService.createOrUpdateEntity( new Reservation(trip.getScheduledTransitions().get(2), trip.getScheduledTransitions().get(4), trip, new Seat(5),"Has", "Mokarov", PAID, ReservationConfirmed.NOT_CONFIRMED, LocalDateTime.now(), ONE_WAY))
    @Test
    public void reservationCreationTest() {
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        reservationService.createOrUpdateEntity(new Reservation(scheduledTrip.getScheduledTransitions().get(2), scheduledTrip.getScheduledTransitions().get(4), scheduledTrip, seatService.findEntityById(5L), "Has", "Mokarov", PAID, ReservationConfirmed.NOT_CONFIRMED, LocalDateTime.now()));
    }

    @Test
    public void deleteReservationPutIntoOpenFolderTest() {
        Reservation deleteReservation = reservationService.findEntityById(2L);
        reservationService.deactivateReservation(deleteReservation);
    }

    @Test
    public void activateReservationRemoveFromOpenFolderTest() {
        OpenFolder openFolder = openFolderService.findEntityById(1L);
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        Seat seat = seatService.findEntityById(6L);

        System.out.println();
        reservationService.activateReservation(openFolder, scheduledTrip, seat);
    }
}
