package com.example.tsh;

import static com.example.tsh.model.enums.ReservationDirections.ONE_WAY;
import static com.example.tsh.model.enums.ReservationPaid.PAID;
import static com.example.tsh.model.enums.TicketStatus.CONFIRMED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.tsh.model.entity.*;
import com.example.tsh.model.enums.Country;
import com.example.tsh.model.enums.ReservationConfirmed;
import com.example.tsh.service.ReservationService;
import com.example.tsh.service.ScheduledTransitionService;
import com.example.tsh.service.ScheduledTripService;
import com.example.tsh.service.SeatService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class ReservationTests {
    @Mock
    private ScheduledTripService scheduledTripService = mock(ScheduledTripService.class);
    @Mock
    private ScheduledTransitionService scheduledTransitionService = mock(ScheduledTransitionService.class);
    @Mock
    private SeatService seatService = mock(SeatService.class);
    @Mock
    private ReservationService reservationService = mock(ReservationService.class);

    @BeforeEach
    void init(){
        Seat[] s = new Seat[40];
        List<Seat> seats = new LinkedList<>();
        for (int i = 0; i < 40; i++) {
            s[i] = new Seat(i+1);
            seats.add(s[i]);

        }

        List<ScheduledTransition> scheduledTransitionList =
                Arrays.asList(new ScheduledTransition(LocalDateTime.now().plusHours(2),new City(Country.BULGARIA, "Sofia"),seats),
                        new ScheduledTransition(LocalDateTime.now().plusHours(3),new City(Country.BULGARIA, "Blagoevgrad"),seats.subList(15, 20)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(4),new City(Country.BULGARIA, "Sandanski"),seats.subList(20, 25)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(5),new City(Country.GREECE, "Solun"),seats.subList(25, 30)),
                        new ScheduledTransition(LocalDateTime.now().plusHours(8),new City(Country.GREECE, "Atina"),seats.subList(30, 40)))
                ;

        List<Driver>drivers=new LinkedList<>(Arrays.asList(new Driver("Shofior","0895433211","0891235665"),
                new Driver("Shofior2","0895430011","0891200665"),
                new Driver("Djivko","0895430019","0891200669"),
                new Driver("Zdravko","0895430111","0891200165")));
    //    trip = new ScheduledTrip(new Bus(60,drivers), scheduledTransitionList);
    }
    // ReservationServiceModel r = new ReservationServiceModel(fr, t, scheduledTrip,seatServiceModel,"Has", "Mokarov", PAID, CONFIRMED, LocalDateTime.now(), ReservationDirections.ONE_WAY);
    //reservationService.createOrUpdateEntity( new Reservation(trip.getScheduledTransitions().get(2), trip.getScheduledTransitions().get(4), trip, new Seat(5),"Has", "Mokarov", PAID, ReservationConfirmed.NOT_CONFIRMED, LocalDateTime.now(), ONE_WAY))
    @Test
    public void testTripCreation(){
         ScheduledTrip trip= new ScheduledTrip();
         trip.setId(1L);
        when(scheduledTripService.createOrUpdateEntity(ArgumentMatchers.any(ScheduledTrip.class))).thenReturn(trip);

       ScheduledTrip scheduledTrip = scheduledTripService.createOrUpdateEntity(trip);

       assertSame(scheduledTrip.getId(), trip.getId());
        verify(scheduledTripService).createOrUpdateEntity(trip);
    }
}
