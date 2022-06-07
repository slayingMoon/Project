package com.example.tsh.service;

import com.example.tsh.model.entity.*;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService extends GenericService<Reservation>{
    void reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);

    OpenFolder deactivateReservation(Reservation reservation);

    Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat);

}
