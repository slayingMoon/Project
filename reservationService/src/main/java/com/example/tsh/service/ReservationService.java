package com.example.tsh.service;

import com.example.tsh.model.entity.*;


import javax.transaction.Transactional;


public interface ReservationService {
    void reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);


    Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat);



    Reservation setStatusDeleted(Reservation reservation);
}
