package com.example.tsh.service.impl;

import com.example.tsh.dao.ScheduledTripRepository;
import com.example.tsh.model.entity.ScheduledTransition;
import com.example.tsh.model.entity.ScheduledTrip;
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
