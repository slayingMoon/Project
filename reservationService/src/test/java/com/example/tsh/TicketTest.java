package com.example.tsh;

import com.example.tsh.model.entity.*;
import com.example.tsh.service.impl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
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
        Reservation reservation = reservationService.findEntityById(6L);
        oneWayTicketService.createOrUpdateEntity(new OneWayTicket(reservation));
    }
    @Test
    public void payOneWayTicket(){
        Reservation reservation = reservationService.findEntityById(8L);
        reservationService.payOneWayReservation(reservation);
    }
    @Test
    public void deleteReservationPutIntoOpenFolderTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(2L);
          oneWayTicketService.moveReservationToOpenFolder(oneWayTicket.getGoToReservation(), oneWayTicket);
    }

    @Test
    public void activateReservationRemoveFromOpenFolderTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(2L);
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        Seat seat = seatService.findEntityById(6L);
       oneWayTicketService.removeReservationFromOpenFolder(oneWayTicket,scheduledTrip, seat);

    }
    @Test
    public void payDoubleWayTicket(){
        Reservation reservation = reservationService.findEntityById(5L);
        reservationService.payDoubleWayReservation(reservation);
    }


}
