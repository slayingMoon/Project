package com.example.tsh.util.validator;

import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.service.impl.ReservationServiceImpl;
import com.example.tsh.service.impl.ScheduledTransitionServiceImpl;
import com.example.tsh.service.impl.ScheduledTripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationValidator {

    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;

    public void validateReservation(Reservation reservation) {
        List<Method> methods = Arrays.asList(this.getClass().getMethods());
        List<Method> methodList = methods
                .stream()
                .filter(e -> e.isAnnotationPresent(ReservationValidation.class))
                .collect(Collectors.toList());

        methodList
                .forEach(ex -> {
                            try {
                                ex.invoke(this, reservation);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }

                );
    }

    @ReservationValidation
    public boolean hasRightDirection(Reservation reservation) {


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

    @ReservationValidation
    public boolean transitionsFromTheSameTrip(Reservation reservation) {
        if (scheduledTripService.findTripByTransition(reservation.getFrom()).equals(scheduledTripService.findTripByTransition(reservation.getTo()))) {
            return true;
        }
        throw new RuntimeException("Transitions are not from the same trip.");
    }
    @ReservationValidation
    public boolean freeSeat(Reservation reservation) {
        if (scheduledTransitionService.getFreeSeats(reservation.getFrom(), reservation.getTo()).contains(reservation.getSeat().getSeatNumber())) {
            return true;
        }
        throw new RuntimeException("Seat is not empty.");
    }
    @ReservationValidation
    public boolean seatCapacityAndSeatNumberCheck(Reservation reservation) {
        if (scheduledTripService.findTripByTransition(reservation.getFrom()).getBus().getSeatCapacity().compareTo(reservation.getSeat().getSeatNumber()) > 0) {
            return true;
        }
        throw new RuntimeException("Seat number can not be bigger than bus seat capacity.");

    }
}
