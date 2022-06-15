package com.example.tsh.service;

import com.example.tsh.model.entity.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface ReservationService {
    void reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);


    Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat);


    @Transactional
    Reservation setStatusDeleted(Reservation reservation);
}
