package com.tsh.frantishex.rervationServiceTest.service;

import com.tsh.frantishex.reservationService.model.entity.*;
import com.tsh.frantishex.reservationService.service.impl.*;
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
public class TicketTest {
    @Autowired
    private OneWayTicketService oneWayTicketService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private SeatServiceImpl seatService;

    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;
    @Autowired
    private DoubleWayTicketServiceImpl doubleWayTicketService;
    @Test
    public void ticketCreation() {
        Reservation reservation = reservationService.findEntityById(2L);
        oneWayTicketService.createOrUpdateEntity(new OneWayTicket(reservation));
    }
    @Test
    public void payOneWayTicket(){
        Reservation reservation = reservationService.findEntityById(3L);
        reservationService.payOneWayReservation(reservation);
    }
    @Test
    public void deleteReservationPutIntoOpenFolderOneWayTicketTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(2L);
          oneWayTicketService.moveGoToReservationToOpenFolder(oneWayTicket);
    }

    @Test
    public void activateReservationRemoveFromOpenFolderOneWayTicketTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(2L);
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        Seat seat = seatService.findEntityById(6L);
       oneWayTicketService.removeGoToReservationFromOpenFolder(oneWayTicket,scheduledTrip, seat);

    }
    @Test
    public void payDoubleWayTicket(){
        Reservation reservation = reservationService.findEntityById(14L);
        reservationService.payDoubleWayReservation(reservation);
    }

    @Test
    public void deleteGoToReservationDoubleWayTicketTicketServiceTest(){
        DoubleWayTicket doubleWayTicket = doubleWayTicketService.findEntityById(3L);
        doubleWayTicketService.moveGoToReservationToOpenFolder(doubleWayTicket );

    }

    @Test
    public void activateReturnReservationDoubleWayTicketTicketServiceTest(){
        doubleWayTicketService.removeReturnReservationFromOpenFolder(doubleWayTicketService.findEntityById(3L),scheduledTripService.findEntityById(1L),seatService.findEntityById(8L));

    }
    @Test
    public void deleteReturnReservationDoubleWayTicketTicketServiceTest(){
        DoubleWayTicket doubleWayTicket = doubleWayTicketService.findEntityById(3L);
        doubleWayTicketService.moveReturnReservationToOpenFolder( doubleWayTicket );

    }
    @Test
    public void activateGoToReservationDoubleWayTicketTicketServiceTest(){
         doubleWayTicketService.removeGoToReservationFromOpenFolder(doubleWayTicketService.findEntityById(3L),scheduledTripService.findEntityById(1L),seatService.findEntityById(33L));

    }


}
