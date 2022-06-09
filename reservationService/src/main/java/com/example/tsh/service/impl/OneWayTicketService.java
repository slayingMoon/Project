package com.example.tsh.service.impl;

import com.example.tsh.dao.OneWayTicketRepository;

import com.example.tsh.model.entity.*;

import com.example.tsh.model.enums.TicketStatus;
import com.example.tsh.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class OneWayTicketService extends GenericServiceImpl<OneWayTicket> {

    @Autowired
    private OneWayTicketRepository oneWayTicketRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private OpenFolderServiceImpl openFolderService;

    @Transactional
    public void moveReservationToOpenFolder(Reservation reservation, OneWayTicket oneWayTicket){
        oneWayTicket.setGoToReservation(null);
        oneWayTicket.setStatus(TicketStatus.OPEN_TICKET);
        oneWayTicketRepository.save(oneWayTicket);
        openFolderService.deactivateReservation(reservation, oneWayTicket.getTicketNo());
    }
    public void removeReservationFromOpenFolder(OneWayTicket oneWayTicket, ScheduledTrip scheduledTrip, Seat seat){
       OpenFolder openFolder = openFolderService.findOpenFolderByTicketNo(oneWayTicket.getTicketNo());
        Reservation reservation = reservationService.activateReservation(openFolder, scheduledTrip, seat);
        oneWayTicket.setStatus(TicketStatus.CONFIRMED);
        oneWayTicket.setGoToReservation(reservation);
        oneWayTicketRepository.save(oneWayTicket);
    }

}
