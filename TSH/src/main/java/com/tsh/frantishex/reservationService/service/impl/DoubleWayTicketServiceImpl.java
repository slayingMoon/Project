package com.tsh.frantishex.reservationService.service.impl;


import com.tsh.frantishex.reservationService.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DoubleWayTicketServiceImpl extends TicketServiceImpl<DoubleWayTicket> {
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private OpenFolderServiceImpl openFolderService;
    @Transactional
    public void moveReturnReservationToOpenFolder(DoubleWayTicket ticket){
        Reservation reservation= ticket.getReturnReservation();
        scheduledTransitionService.returnSeat(reservation.getFrom(),reservation.getTo(),reservation.getSeat());
        ticket.setReturnReservation(null);
        repository.save(ticket);
        openFolderService.moveToOpenFolder(reservation, ticket.getTicketNumber());

    }

    @Transactional
    public void removeReturnReservationFromOpenFolder(DoubleWayTicket ticket, ScheduledTrip scheduledTrip, Seat seat){
        OpenFolder openFolder = openFolderService.findOpenFolderByTicketNo(ticket.getTicketNumber());
        Reservation reservation = reservationService.activateReservation(openFolder, scheduledTrip, seat);
        ticket.setReturnReservation(reservation);
        repository.save(ticket);
    }
}
