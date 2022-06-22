package com.example.tsh.service.impl;

import com.example.tsh.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TicketServiceImpl<T extends OneWayTicket > extends GenericServiceImpl<T>{
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private OpenFolderServiceImpl openFolderService;


    @Transactional
    public void moveGoToReservationToOpenFolder(T ticket){
        Reservation reservation=ticket.getGoToReservation();
        scheduledTransitionService.returnSeat(reservation.getFrom(),reservation.getTo(),reservation.getSeat());
        ticket.setGoToReservation(null);
        repository.save(ticket);
        openFolderService.moveToOpenFolder(reservation, ticket.getTicketNumber());

    }

    @Transactional
    public void removeGoToReservationFromOpenFolder(T ticket, ScheduledTrip scheduledTrip, Seat seat){
        OpenFolder openFolder = openFolderService.findOpenFolderByTicketNo(ticket.getTicketNumber());
        Reservation reservation = reservationService.activateReservation(openFolder, scheduledTrip, seat);
        ticket.setGoToReservation(reservation);
        repository.save(ticket);
    }

}
