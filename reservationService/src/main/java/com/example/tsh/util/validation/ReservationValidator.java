package com.example.tsh.util.validation;

import com.example.tsh.model.entity.Reservation;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReservationValidator implements ConstraintValidator<ValidReservation, Reservation> {
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;

    @Override
    public void initialize(ValidReservation constraintAnnotation) {
     //   ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext constraintValidatorContext) {
        return !scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber());
    }
}
