package com.example.tsh.service.impl;

import com.example.tsh.dao.OpenFolderRepository;
import com.example.tsh.model.entity.Direction;
import com.example.tsh.model.entity.OpenFolder;
import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.TicketNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class OpenFolderServiceImpl extends GenericServiceImpl<OpenFolder>  {
      @Autowired
      private ReservationServiceImpl reservationService;
      @Autowired
      private ScheduledTransitionServiceImpl scheduledTransitionService;
      @Autowired
      private OpenFolderRepository openFolderRepository;

    private OpenFolder getOpenFolder(Reservation reservation, TicketNo num, Direction direction) {

        OpenFolder openFolder=new OpenFolder();
        openFolder.setPassenger(reservation.getPassenger());
        openFolder.setExpirationDate(LocalDateTime.now().plusYears(1));
        openFolder.setDirection(direction);
        openFolder.setReservationCreationDate(reservation.getReservationDate());
        openFolder.setTicketNo(num);
        return openFolder;


    }
    public OpenFolder getOpenFolderWithReversedDirections(Reservation reservation, TicketNo ticketNo){
        OpenFolder openFolder = getOpenFolder(reservation, ticketNo, new Direction(reservation.getTo().getCity(), reservation.getFrom().getCity()));
        return createOrUpdateEntity(openFolder);
    }
    public OpenFolder moveToOpenFolder(Reservation reservation, TicketNo num){
        OpenFolder openFolder = getOpenFolder(reservation, num,new Direction(reservation.getFrom().getCity(),reservation.getTo().getCity()));
        scheduledTransitionService.returnSeat(reservation.getFrom(),reservation.getTo(),reservation.getSeat());
        reservationService.delete(reservation);
        return createOrUpdateEntity(openFolder);
    }

    public OpenFolder findOpenFolderByTicketNo(TicketNo ticketNo)
    {
        return openFolderRepository.findAll().stream()
                .filter(e->e.getTicketNo().equals(ticketNo))
                .findFirst()
                .orElse(null);
    }


}
