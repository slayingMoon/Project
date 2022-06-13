package com.example.tsh;

import com.example.tsh.model.entity.*;
import com.example.tsh.service.impl.OneWayTicketService;
import com.example.tsh.service.impl.ReservationServiceImpl;
import com.example.tsh.service.impl.ScheduledTripServiceImpl;
import com.example.tsh.service.impl.SeatServiceImpl;
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
    @Test
    public void ticketCreation() {
        Reservation reservation = reservationService.findEntityById(2L);
        oneWayTicketService.createOrUpdateEntity(new OneWayTicket(reservation));
    }
    @Test
    public void deleteReservationPutIntoOpenFolderTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(1L);
          oneWayTicketService.moveReservationToOpenFolder(oneWayTicket.getGoToReservation(), oneWayTicket);
    }

    @Test
    public void activateReservationRemoveFromOpenFolderTest() {
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(1L);
        ScheduledTrip scheduledTrip = scheduledTripService.findEntityById(1L);
        Seat seat = seatService.findEntityById(6L);
       oneWayTicketService.removeReservationFromOpenFolder(oneWayTicket,scheduledTrip, seat);

    }

    @Test
    public void testingTicketDeleting(){
        OneWayTicket oneWayTicket = oneWayTicketService.findEntityById(1L);
        oneWayTicketService.deleteTicket(oneWayTicket);
    }
}
