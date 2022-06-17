package com.example.tsh.service.impl;

import com.example.tsh.dao.DoubleWayTicketRepository;
import com.example.tsh.model.entity.*;
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
    public void moveReturnReservationToOpenFolder(Reservation reservation, DoubleWayTicket ticket){
        scheduledTransitionService.returnSeat(reservation.getFrom(),reservation.getTo(),reservation.getSeat());
        ticket.setReturnReservation(null);
        repository.save(ticket);
        openFolderService.moveToOpenFolder(reservation, ticket.getTicketNo());

    }

    @Transactional
    public void removeReturnReservationFromOpenFolder(DoubleWayTicket ticket, ScheduledTrip scheduledTrip, Seat seat){
        OpenFolder openFolder = openFolderService.findOpenFolderByTicketNo(ticket.getTicketNo());
        Reservation reservation = reservationService.activateReservation(openFolder, scheduledTrip, seat);
        ticket.setGoToReservation(reservation);
        repository.save(ticket);
    }
}
