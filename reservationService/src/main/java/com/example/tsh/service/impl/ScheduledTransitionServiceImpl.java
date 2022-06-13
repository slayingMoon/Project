package com.example.tsh.service.impl;

import com.example.tsh.model.entity.City;
import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.model.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ScheduledTransitionServiceImpl extends GenericServiceImpl<ScheduledTransition>  {
    @Autowired
    private ScheduledTripServiceImpl scheduledTripService;
    public void returnSeat(List<ScheduledTransition> filteredFromTo, Seat seat) {
        filteredFromTo
                .forEach(e -> {
                            e.getSeats().remove(seat);
                           createOrUpdateEntity(e);
                        }
                );

    }
    public void reserveSeat(List<ScheduledTransition> filteredFromTo, Seat seat) {
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
        ScheduledTrip trip=scheduledTripService.findTripByTransition(from);
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
    private List<Integer> getFreeSeats(List<ScheduledTransition> filteredFromTo, Integer busCapacity) {

        List<Integer> freeSeats = IntStream.rangeClosed(1, busCapacity).boxed().collect(Collectors.toList());
        System.out.println();
        //  List<Integer> freeSeats = new LinkedList<>();
        // for (int i = 1; i <= busCapacity; i++) {
        //    freeSeats.add(i);
        //}

        filteredFromTo.stream()
                .flatMap(s -> s.getSeats().stream())
                .forEach(seat -> {
                    if (freeSeats.contains(seat.getSeat())) {
                        freeSeats.remove(seat.getSeat());
                    }
                });

        return freeSeats;
    }

}
