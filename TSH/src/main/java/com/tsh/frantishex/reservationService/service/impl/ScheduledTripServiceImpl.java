package com.tsh.frantishex.reservationService.service.impl;

import com.tsh.frantishex.reservationService.dao.ScheduledTripRepository;
import com.tsh.frantishex.reservationService.model.entity.*;
import com.tsh.frantishex.tripService.domain.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ScheduledTripServiceImpl extends GenericServiceImpl<ScheduledTrip> {
    @Autowired
    private ScheduledTripRepository scheduledTripRepository;

    public ScheduledTrip findTripByTransition(ScheduledTransition scheduledTransition) {
        return scheduledTripRepository.findAll()
                .stream()
                .filter(e -> e.getScheduledTransitions().contains(scheduledTransition))
                .findFirst()
                .orElse(null);

    }

    public ScheduledTrip createScheduledTripFromTrip(Tripp trip, LocalDate date, List<Driver> drivers) {
        Bus bus = new Bus(60, drivers);
        List<ScheduledTransition> scheduledTransitions = new ArrayList<>();
        trip.getTransitions()
                .forEach(e -> {
                    scheduledTransitions.add(new ScheduledTransition(LocalDateTime.of(date, trip.getDepartureTime().plusHours(e.getTravelDuration())), new ReservationCity(e.getTripCity().getName()), new ArrayList<>()));
                });
        return createOrUpdateEntity(new ScheduledTrip(bus, scheduledTransitions));
    }


}
