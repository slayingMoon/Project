package com.tsh.frantishex.rervationServiceTest.service;

import com.tsh.frantishex.reservationService.model.entity.*;
import com.tsh.frantishex.reservationService.service.impl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/reservationServiceProperties/data-reservation.sql")
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
    @Transactional
    @Rollback
    public void ticketCreation() {
        Reservation reservation = reservationService.findEntityById(7L);
        oneWayTicketService.createOrUpdateEntity(new OneWayTicket(reservation));
    }
    @Test
    @Transactional
    @Rollback
    public void payOneWayTicket(){
        Reservation reservation = reservationService.findEntityById(7L);
        reservationService.payOneWayReservation(reservation);
    }
    @Test
    @Transactional
    @Rollback
    public void deleteReservationPutIntoOpenFolderOneWayTicketTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(2L);
          oneWayTicketService.moveGoToReservationToOpenFolder(oneWayTicket);
    }

    @Test
    @Transactional
    @Rollback
    public void activateReservationRemoveFromOpenFolderOneWayTicketTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(3L);
        oneWayTicketService.moveGoToReservationToOpenFolder(oneWayTicket);
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        Seat seat = seatService.findEntityById(15L);

       oneWayTicketService.removeGoToReservationFromOpenFolder(oneWayTicket,scheduledTrip, seat);

    }
    @Test
    @Transactional
    @Rollback
    public void payDoubleWayTicket(){
        Reservation reservation = reservationService.findEntityById(14L);
        reservationService.payDoubleWayReservation(reservation);
    }

    @Test
    @Transactional
    @Rollback
    public void activateReturnReservationDoubleWayTicketTicketServiceTest(){
        DoubleWayTicket doubleWayTicket = doubleWayTicketService.findEntityById(3L);
        doubleWayTicketService.moveGoToReservationToOpenFolder(doubleWayTicket );
        doubleWayTicketService.removeReturnReservationFromOpenFolder(doubleWayTicket,scheduledTripService.findEntityById(1L),seatService.findEntityById(8L));

    }

    @Test
    @Transactional
    @Rollback
    public void activateGoToReservationDoubleWayTicketTicketServiceTest(){
        DoubleWayTicket doubleWayTicket = doubleWayTicketService.findEntityById(3L);
        doubleWayTicketService.moveGoToReservationToOpenFolder( doubleWayTicket );
         doubleWayTicketService.removeGoToReservationFromOpenFolder(doubleWayTicket,scheduledTripService.findEntityById(1L),seatService.findEntityById(15L));

    }


}
