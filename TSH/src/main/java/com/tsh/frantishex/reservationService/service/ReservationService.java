package com.tsh.frantishex.reservationService.service;



import com.tsh.frantishex.reservationService.model.entity.*;

import javax.transaction.Transactional;


public interface ReservationService {
    void reserve(Reservation reservation);


    OneWayTicket payOneWayReservation(Reservation reservation);


    Reservation activateReservation(OpenFolder openFolder, ScheduledTrip scheduledTrip, Seat seat);



    Reservation setStatusDeleted(Reservation reservation);
}
