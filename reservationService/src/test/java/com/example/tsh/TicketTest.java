package com.example.tsh;

import com.example.tsh.model.entity.OneWayTicket;
import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.model.enums.ReservationConfirmed;
import com.example.tsh.model.enums.TicketStatus;
import com.example.tsh.service.ReservationService;
import com.example.tsh.service.impl.OneWayTicketService;
import com.example.tsh.service.impl.ReservationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static com.example.tsh.model.enums.ReservationPaid.PAID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {
    @Autowired
    private OneWayTicketService oneWayTicketService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Test
    public void reservationCreationTest() {
        Reservation reservation = reservationService.findEntityById(1L);

        oneWayTicketService.createOrUpdateEntity(new OneWayTicket(reservation, TicketStatus.CONFIRMED));
    }
}
