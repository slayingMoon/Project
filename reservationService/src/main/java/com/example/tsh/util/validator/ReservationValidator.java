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
    public void validateReservation(Reservation reservation){
        if(!hasRightDirection(reservation)){
            throw new RuntimeException("Direction is not right.");
        }
        if(!transitionsFromTheSameTrip(reservation)){
            throw new RuntimeException("Transitions are not from the same trip.");
        }
        if(!scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())){
            throw new RuntimeException("Seat is not empty.");
        }
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
    private boolean transitionsFromTheSameTrip(Reservation reservation){
        return scheduledTripService.findTripByTransition(reservation.getFrom()).equals(scheduledTripService.findTripByTransition(reservation.getTo()));
    }
}
