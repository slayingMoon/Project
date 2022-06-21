package com.example.tsh.util.validator;

import com.example.tsh.model.entity.Reservation;
import com.example.tsh.service.impl.ReservationServiceImpl;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationValidator {
   @Autowired
   private ScheduledTransitionServiceImpl scheduledTransitionService;
   @Autowired
   private ReservationServiceImpl reservationService;

    public void validateReservation(Reservation reservation){
        if(!reservationService.hasRightDirection(reservation)){
            throw new RuntimeException("Direction is not right.");
        }
        if(!scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())){
            throw new RuntimeException("Seat is not empty.");
        }
    }
}
