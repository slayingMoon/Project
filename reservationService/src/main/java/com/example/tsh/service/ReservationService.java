package com.example.tsh.service;

import com.example.tsh.model.entity.OneWayTicket;
import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.ScheduledTrip;
import com.example.tsh.model.entity.Seat;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService extends GenericService<Reservation>{
    void reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);

    Reservation deactivateReservation(Reservation reservation);

    Reservation activateReservation(Reservation reservation, ScheduledTrip scheduledTrip, Seat seat);

    void deleteReservation(Reservation reservation);
}
