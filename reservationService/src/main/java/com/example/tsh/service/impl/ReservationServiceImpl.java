package com.example.tsh.service.impl;

import com.example.tsh.dao.ReservationRepository;
import com.example.tsh.model.entity.*;
import com.example.tsh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static com.example.tsh.model.enums.ReservationStatus.CONFIRMED;


@Component
public class ReservationServiceImpl extends GenericServiceImpl< Reservation> implements ReservationService {


    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private OpenFolderServiceImpl openFolderService;

    @Override
    public void reserve(Reservation reservation) {


        reserveSeat(filteredFromTo(reservation.getScheduledTrip(), reservation.getFrom(), reservation.getTo()), reservation.getSeat());
        System.out.println(reservation);
        createOrUpdateEntity(reservation);
    }

    @Override
    public OneWayTicket payOneWayReservation(Reservation reservation) {


        reservation.setReservationStatus(CONFIRMED);
        OneWayTicket oneWayTicket = new OneWayTicket();
        oneWayTicket.setGoToReservation(reservation);

        return oneWayTicket;




    }




    @Override
    @Transactional
    public Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat) {
        Reservation reservation= new Reservation();
        reservation.setFirstName(openFolder.getFirstName());
        reservation.setLastName(openFolder.getLastName());
        reservation.setSeat(seat);
        reservation.setReservationStatus(CONFIRMED);
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


    private OpenFolder reverseReservationTransitions(Reservation reservation){
       OpenFolder openFolder = new OpenFolder();
        ScheduledTransition fromTransition = reservation.getTo();
        ScheduledTransition toTransition = reservation.getFrom();

        reservation.setFrom(fromTransition);
        reservation.setTo(toTransition);

        return openFolder;

    }

    private ScheduledTransition findTransitionByTrip(ScheduledTrip trip, City transition) {
        return trip.getScheduledTransitions()
                .stream()
                .filter(e -> e.getCity().equals(transition))
                .findFirst()
                .orElse(null);
    }

    private List<Integer> getFreeSeats(List<ScheduledTransition> filteredFromTo, Integer busCapacity) {

        IntStream.rangeClosed(1, busCapacity).toArray();

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

