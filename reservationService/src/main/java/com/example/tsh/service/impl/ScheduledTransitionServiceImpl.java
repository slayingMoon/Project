package com.example.tsh.service.impl;

import com.example.tsh.model.entity.City;
import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.model.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ScheduledTransitionServiceImpl extends GenericServiceImpl<ScheduledTransition> {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;



    public void returnSeat(ScheduledTransition from, ScheduledTransition to, Seat seat) {
        List<ScheduledTransition> filteredFromTo = filteredFromTo(from, to);
        filteredFromTo
                .forEach(e -> {
                            e.getSeats().remove(seat);
                            createOrUpdateEntity(e);
                        }
                );

    }

    public void reserveSeat(ScheduledTransition from, ScheduledTransition to, Seat seat) {
        List<ScheduledTransition> filteredFromTo = filteredFromTo(from, to);
        filteredFromTo
                .forEach(e -> {
                            e.getSeats().add(seat);
                            createOrUpdateEntity(e);
                        }
                );

    }

    public ScheduledTransition findTransitionByTrip(ScheduledTrip trip, City transition) {
        return trip.getScheduledTransitions()
                .stream()
                .filter(e -> e.getCity().equals(transition))
                .findFirst()
                .orElse(null);
    }

    public List<ScheduledTransition> filteredFromTo(ScheduledTransition from, ScheduledTransition to) {
        ScheduledTrip trip = scheduledTripService.findTripByTransition(from);
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

    public List<Integer> getFreeSeats(ScheduledTransition from, ScheduledTransition to) {
        ScheduledTrip scheduledTrip= scheduledTripService.findTripByTransition(from);
        Integer busCapacity = scheduledTrip.getBus().getSeatCapacity();
       List<ScheduledTransition> filteredFromTo=filteredFromTo(from, to);
        List<Integer> freeSeats = IntStream.rangeClosed(1, busCapacity).boxed().collect(Collectors.toList());


        filteredFromTo.stream()
                .flatMap(s -> s.getSeats().stream())
                .forEach(seat -> {
                    freeSeats.remove(seat.getSeatNumber());
                });

        return freeSeats;
    }

}
