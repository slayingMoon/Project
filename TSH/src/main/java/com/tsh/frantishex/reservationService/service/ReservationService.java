package com.tsh.frantishex.reservationService.service;



import com.tsh.frantishex.reservationService.model.entity.*;

import javax.transaction.Transactional;


public interface ReservationService {
    Reservation reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);


    @Transactional
    DoubleWayTicket payDoubleWayReservation(Reservation reservation);

    Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat);



    Reservation setStatusDeleted(Reservation reservation);
}
