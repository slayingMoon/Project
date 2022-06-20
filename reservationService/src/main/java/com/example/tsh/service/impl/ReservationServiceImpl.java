package com.example.tsh.service.impl;


import com.example.tsh.model.entity.*;
import com.example.tsh.service.*;
import com.example.tsh.util.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.tsh.model.enums.ReservationStatus.CONFIRMED;
import static com.example.tsh.model.enums.ReservationStatus.DELETED;


@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation> implements ReservationService {


    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    @Autowired
    private OpenFolderServiceImpl openFolderService;
    @Autowired
    private OneWayTicketService oneWayTicketService;
    @Autowired
    private DoubleWayTicketServiceImpl doubleWayTicketService;


    @Override
    @Transactional
    public void reserve( Reservation reservation) {
        if(!scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())){
            throw new RuntimeException("Seat is not empty.");
        }
        scheduledTransitionService.reserveSeat(reservation.getFrom(), reservation.getTo(), reservation.getSeat());
        createOrUpdateEntity(reservation);
    }

    @Override
    @Transactional
    public OneWayTicket payOneWayReservation(Reservation reservation) {

        reservation.setReservationStatus(CONFIRMED);
        OneWayTicket oneWayTicket = new OneWayTicket();
        oneWayTicket.setGoToReservation(reservation);
        oneWayTicketService.createOrUpdateEntity(oneWayTicket);
        return oneWayTicket;

    }

    @Transactional
    public DoubleWayTicket payDoubleWayReservation(Reservation reservation) {
        reservation.setReservationStatus(CONFIRMED);
        DoubleWayTicket doubleWayTicket = new DoubleWayTicket();
        doubleWayTicket.setGoToReservation(reservation);
        doubleWayTicket.setReturnReservation(null);
        DoubleWayTicket newTick = doubleWayTicketService.createOrUpdateEntity(doubleWayTicket);
        openFolderService.getOpenFolderWithReversedDirections(reservation,newTick.getTicketNumber());
        return doubleWayTicket;
    }

    @Override
    @Transactional
    public Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat) {
        Reservation reservation = new Reservation();
        reservation.setPassenger(openFolder.getPassenger());
        reservation.setSeat(seat);
        reservation.setReservationStatus(CONFIRMED);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setFrom(scheduledTransitionService.findTransitionByTrip(scheduledTrip, openFolder.getDirection().getFrom()));
        reservation.setTo(scheduledTransitionService.findTransitionByTrip(scheduledTrip, openFolder.getDirection().getTo()));
        reservation.setReservationDate(openFolder.getReservationCreationDate());
        //TODO validation
        openFolderService.delete(openFolder);
        reserve(reservation);
        return reservation;
    }

    @Override
    @Transactional
    public Reservation setStatusDeleted(Reservation reservation){
        reservation.setReservationStatus(DELETED);
        scheduledTransitionService.returnSeat(reservation.getFrom(),reservation.getTo(),reservation.getSeat());
        return createOrUpdateEntity(reservation);
    }


    private boolean hasRightDirection(Reservation reservation) {


        ScheduledTransition from = reservation.getFrom();
        ScheduledTransition to = reservation.getTo();
        ScheduledTrip scheduledTrip = scheduledTripService.findTripByTransition(from);
        boolean right = true;
        List<ScheduledTransition> transitionList = new ArrayList<>(scheduledTrip.getScheduledTransitions());
        int fromIndex = transitionList.indexOf(from);
        int toIndex = transitionList.indexOf(to) + 1;
        if (fromIndex > toIndex) {
            right = false;
        }
        return right;
    }


}

