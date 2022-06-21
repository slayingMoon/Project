package com.example.tsh.util.validator;

import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.service.impl.ReservationServiceImpl;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;
import com.example.tsh.service.impl.ScheduledTripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationValidator {
    //valid
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    public void validateReservation(Reservation reservation) {

    }

    private boolean hasRightDirection(Reservation reservation) {


        ScheduledTransition from = reservation.getFrom();
        ScheduledTransition to = reservation.getTo();
        ScheduledTrip scheduledTrip = scheduledTripService.findTripByTransition(from);
        List<ScheduledTransition> transitionList = new ArrayList<>(scheduledTrip.getScheduledTransitions());
        int fromIndex = transitionList.indexOf(from);
        int toIndex = transitionList.indexOf(to) + 1;
        if (fromIndex > toIndex) {
            throw new RuntimeException("Direction is not right.");
        }
        return true;
    }

    private boolean transitionsFromTheSameTrip(Reservation reservation) {
        if (scheduledTripService.findTripByTransition(reservation.getFrom()).equals(scheduledTripService.findTripByTransition(reservation.getTo()))) {
            return true;
        }
        throw new RuntimeException("Transitions are not from the same trip.");
    }

    private boolean freeSeat(Reservation reservation) {
        if (scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())) {
            return true;
        }
        throw new RuntimeException("Seat is not empty.");
    }

    private boolean seatCapacity(Reservation reservation) {
        if (scheduledTripService.findTripByTransition(reservation.getFrom()).getBus().getSeatCapacity().compareTo(reservation.getSeat().getSeatNumber()) > 0) {
            return true;
        }
        throw new RuntimeException("Seat number can not be bigger than bus seat capacity.");

    }
}
