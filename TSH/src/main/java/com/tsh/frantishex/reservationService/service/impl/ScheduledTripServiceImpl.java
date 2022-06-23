package com.tsh.frantishex.reservationService.service.impl;

import com.tsh.frantishex.reservationService.dao.ScheduledTripRepository;
import com.tsh.frantishex.reservationService.model.entity.ScheduledTransition;
import com.tsh.frantishex.reservationService.model.entity.ScheduledTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTripServiceImpl extends GenericServiceImpl<ScheduledTrip> {
    @Autowired
    private ScheduledTripRepository scheduledTripRepository;
    public ScheduledTrip findTripByTransition(ScheduledTransition scheduledTransition) {
      return scheduledTripRepository.findAll()
                .stream()
                .filter(e->e.getScheduledTransitions().contains(scheduledTransition))
                .findFirst()
                .orElse(null);

    }
}
