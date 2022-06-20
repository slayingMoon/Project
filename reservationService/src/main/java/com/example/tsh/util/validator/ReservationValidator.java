package com.example.tsh.util.validator;

import com.example.tsh.model.entity.Reservation;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;



public class ReservationValidator {


    public static void validateReservation(Reservation reservation, ScheduledTransitionServiceImpl scheduledTransitionService){
        if(!scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())){
            throw new RuntimeException("Seat is not empty.");
        }
    }
}
