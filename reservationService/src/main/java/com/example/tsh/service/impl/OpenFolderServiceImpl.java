package com.example.tsh.service.impl;

import com.example.tsh.dao.OpenFolderRepository;
import com.example.tsh.model.entity.Direction;
import com.example.tsh.model.entity.OpenFolder;
import com.example.tsh.model.entity.Reservation;
import com.example.tsh.model.entity.TicketNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class OpenFolderServiceImpl extends GenericServiceImpl<OpenFolder>  {
      @Autowired
      private ReservationServiceImpl reservationService;
      @Autowired
      private OpenFolderRepository openFolderRepository;

    @Transactional
    public OpenFolder deactivateReservation(Reservation reservation, TicketNo num) {
        OpenFolder openFolder=new OpenFolder();
        openFolder.setFirstName(reservation.getFirstName());
        openFolder.setLastName(reservation.getLastName());
        openFolder.setExpirationDate(LocalDateTime.now().plusYears(1));
        openFolder.setDirection(new Direction(reservation.getFrom().getCity(),reservation.getTo().getCity()));
        openFolder.setReservationCreationDate(reservation.getReservationDate());
        openFolder.setTicketNo(num);
        reservationService.delete(reservation);
        return createOrUpdateEntity(openFolder);

    }
    public OpenFolder findOpenFolderByTicketNo(TicketNo ticketNo)
    {
        return openFolderRepository.findAll().stream()
                .filter(e->e.getTicketNo().equals(ticketNo))
                .findFirst().orElse(null);
    }
}
