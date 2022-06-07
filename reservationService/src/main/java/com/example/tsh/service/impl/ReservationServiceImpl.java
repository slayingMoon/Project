package com.example.tsh.service.impl;

import com.example.tsh.dao.ReservationRepository;
import com.example.tsh.model.entity.*;
import com.example.tsh.model.enums.TicketStatus;
import com.example.tsh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.tsh.model.enums.ReservationConfirmed.CONFIRMED;
import static com.example.tsh.model.enums.ReservationConfirmed.NOT_CONFIRMED;
import static com.example.tsh.model.enums.ReservationPaid.PAID;

@Component
public class ReservationServiceImpl extends GenericServiceImpl< Reservation> implements ReservationService {


    @Autowired
    private ScheduledTransitionService scheduledTransitionService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private OpenFolderService openFolderService;

    @Override
    public void reserve(Reservation reservation) {


        reserveSeat(filteredFromTo(reservation.getScheduledTrip(), reservation.getFrom(), reservation.getTo()), reservation.getSeat());
        System.out.println(reservation);
        createOrUpdateEntity(reservation);
    }

    @Override
    public OneWayTicket payOneWayReservation(Reservation reservation) {

        reservation.setIsPaid(PAID);
        reservation.setIsConfirmed(CONFIRMED);
        OneWayTicket oneWayTicket = new OneWayTicket();
        oneWayTicket.setGoToReservation(reservation);
        oneWayTicket.setStatus(TicketStatus.CONFIRMED);

        return oneWayTicket;




    }

     private Reservation reverseReservationTransitions(Reservation reservation){
        ScheduledTransition fromTransition = reservation.getTo();
        ScheduledTransition toTransition = reservation.getFrom();

        reservation.setFrom(fromTransition);
        reservation.setTo(toTransition);

        return reservation;

    }
    @Override
    @Transactional
    public OpenFolder deactivateReservation(Reservation reservation) {
       OpenFolder openFolder=new OpenFolder();
       openFolder.setFirstName(reservation.getFirstName());
        openFolder.setLastName(reservation.getLastName());
        openFolder.setExpirationDate(LocalDateTime.now().plusYears(1));
        openFolder.setDirection(new Direction(reservation.getFrom().getCity(),reservation.getTo().getCity()));
        openFolder.setReservationCreationDate(reservation.getReservationDate());

        delete(reservation);
        return openFolderService.createOrUpdateEntity(openFolder);

    }

    @Override
    @Transactional
    public Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat) {
        Reservation reservation= new Reservation();
        reservation.setFirstName(openFolder.getFirstName());
        reservation.setLastName(openFolder.getLastName());
        reservation.setSeat(seat);
        reservation.setIsPaid(PAID);
        reservation.setIsConfirmed(CONFIRMED);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setScheduledTrip(scheduledTrip);
        reservation.setFrom(findTransitionByTrip(scheduledTrip,openFolder.getDirection().getFrom()));
        reservation.setTo(findTransitionByTrip(scheduledTrip, openFolder.getDirection().getTo()));
        reservation.setReservationDate(openFolder.getReservationCreationDate());
        //TODO validation
        openFolderService.delete(openFolder);
        createOrUpdateEntity(reservation);
        return reservation;
    }




    private ScheduledTransition findTransitionByTrip(ScheduledTrip trip, City transition) {
        return trip.getScheduledTransitions()
                .stream()
                .filter(e -> e.getCity().equals(transition))
                .findFirst()
                .orElse(null);
    }

    private List<Integer> getFreeSeats(List<ScheduledTransition> filteredFromTo, Integer busCapacity) {


        List<Integer> freeSeats = new LinkedList<>();
        for (int i = 1; i <= busCapacity; i++) {
            freeSeats.add(i);
        }

        filteredFromTo.stream()
                .flatMap(s -> s.getSeats().stream())
                .forEach(seat -> {
                    if (freeSeats.contains(seat.getSeat())) {
                        freeSeats.remove(seat.getSeat());
                    }
                });

        return freeSeats;
    }


    private boolean hasRightDirection(Reservation reservation) {
        ScheduledTrip scheduledTrip = reservation.getScheduledTrip();
        ScheduledTransition from = reservation.getFrom();
        ScheduledTransition to = reservation.getTo();
        boolean right = true;
        List<ScheduledTransition> transitionList = new ArrayList<>(scheduledTrip.getScheduledTransitions());
        int fromIndex = transitionList.indexOf(from);
        int toIndex = transitionList.indexOf(to) + 1;
        if (fromIndex > toIndex) {
            right = false;
        }
        return right;
    }


    private List<ScheduledTransition> filteredFromTo(ScheduledTrip trip, ScheduledTransition from, ScheduledTransition to) {
        AtomicBoolean isBetween = new AtomicBoolean(false);
        List<ScheduledTransition> filteredTransitions = new ArrayList<>();
        trip.getScheduledTransitions()
                .forEach(scheduledTransition -> {
                    if (scheduledTransition.equals(from)) {
                        isBetween.set(true);
                    }
                    if (scheduledTransition.equals(to)) {
                        isBetween.set(false);
                    }
                    if (isBetween.get()) {
                        filteredTransitions.add(scheduledTransition);
                    }
                });
        return filteredTransitions;
    }

    private void reserveSeat(List<ScheduledTransition> filteredFromTo, Seat seat) {
        filteredFromTo
                .forEach(e -> {
                            e.getSeats().add(seat);
                            scheduledTransitionService.createOrUpdateEntity(e);
                        }
                );

    }


}

