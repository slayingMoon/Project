package com.example.tsh.component;

import com.example.tsh.dao.*;
import com.example.tsh.model.dto.ReservationServiceModel;
import com.example.tsh.model.dto.ScheduledTransitionServiceModel;
import com.example.tsh.model.dto.ScheduledTripServiceModel;
import com.example.tsh.model.dto.SeatServiceModel;
import com.example.tsh.model.entity.*;
import com.example.tsh.model.enums.Country;
import com.example.tsh.model.enums.ReservationDirections;
import com.example.tsh.service.ReservationService;
import com.example.tsh.service.ScheduledTransitionService;
import com.example.tsh.service.ScheduledTripService;
import com.example.tsh.service.SeatService;
import com.example.tsh.util.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.tsh.model.enums.ReservationConfirmed.CONFIRMED;
import static com.example.tsh.model.enums.ReservationPaid.PAID;

@Service
public class IvokeService {

    @Autowired
    private ScheduledTripRepository scheduledTripRepository;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ScheduledTripService scheduledTripService;
    @Autowired
    private ScheduledTransitionService scheduledTransitionService;
    @Autowired
    private SeatService seatService;
    @PostConstruct
    public void innit() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //TODO namira free seats
     //  reservationService.getFreeSeats(1L, "Blagoevgrad", "Solun");
        //TODO put reservation in open folder test
//       ReservationServiceModel rsm= reservationService.findEntityById(2L);
//        System.out.println(rsm);
//      ReservationServiceModel rs =   reservationService.openedReservation(rsm);
//        System.out.println();

       //TODO test reservation activation

//        ScheduledTripServiceModel scheduledTrip = scheduledTripService.findEntityById(1L);
//        SeatServiceModel seatServiceModel = seatService.findEntityById(7L);
//        ReservationServiceModel reservationServiceModel=reservationService.findEntityById(1L);
//        reservationService.activateReservation(reservationServiceModel,scheduledTrip,seatServiceModel);
//TODO za create reservation

//        ScheduledTransitionServiceModel fr = scheduledTransitionService.findEntityById(2L);
//        ScheduledTransitionServiceModel t = scheduledTransitionService.findEntityById(4L);
//        ScheduledTripServiceModel scheduledTrip = scheduledTripService.findEntityById(1L);
//        SeatServiceModel seatServiceModel = seatService.findEntityById(3L);
//        ReservationServiceModel r = new ReservationServiceModel(fr, t, scheduledTrip,seatServiceModel,"Has", "Mokarov", PAID, CONFIRMED, LocalDateTime.now(), ReservationDirections.ONE_WAY);
//        System.out.println();
//        reservationService.reserve(r);


//TODO za populate

//     populateOrPrint();
    }
    public void populateOrPrint(){
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
        scheduledTripRepository.saveAndFlush(new ScheduledTrip(new Bus(60,drivers), scheduledTransitionList));

       //System.out.println(scheduledTripService.findScheduledTrip(1L));
        // System.out.println(scheduledTripService.findScheduledTrip(1L).getScheduledTransitions().stream().map(e -> e.getSeats().size()).reduce(0, Integer::sum, Integer::sum) );
        // System.out.println(scheduledTripService.findScheduledTrip(1L).getScheduledTransitions());
    }

}
