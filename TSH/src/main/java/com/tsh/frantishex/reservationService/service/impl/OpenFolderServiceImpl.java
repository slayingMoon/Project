package com.tsh.frantishex.reservationService.service.impl;

import com.tsh.frantishex.reservationService.dao.OpenFolderRepository;
import com.tsh.frantishex.reservationService.model.entity.Direction;
import com.tsh.frantishex.reservationService.model.entity.OpenFolder;
import com.tsh.frantishex.reservationService.model.entity.Reservation;
import com.tsh.frantishex.reservationService.model.entity.TicketNumber;
import com.tsh.frantishex.reservationService.model.enums.OpenFolderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenFolderServiceImpl extends GenericServiceImpl<OpenFolder> {
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private ScheduledTransitionServiceImpl scheduledTransitionService;
    @Autowired
    private OpenFolderRepository openFolderRepository;

    private OpenFolder getOpenFolder(Reservation reservation, TicketNumber num, Direction direction) {

        OpenFolder openFolder = new OpenFolder();
        openFolder.setPassenger(reservation.getPassenger());
        openFolder.setExpirationDate(LocalDateTime.now().plusYears(1));
        openFolder.setDirection(direction);
        openFolder.setReservationCreationDate(reservation.getReservationDate());
        openFolder.setTicketNo(num);
        return openFolder;


    }

    public OpenFolder getOpenFolderWithReversedDirections(Reservation reservation, TicketNumber ticketNo) {
        OpenFolder openFolder = getOpenFolder(reservation, ticketNo, new Direction(reservation.getTo().getCity(), reservation.getFrom().getCity()));
        return createOrUpdateEntity(openFolder);
    }

    public OpenFolder moveToOpenFolder(Reservation reservation, TicketNumber num) {
        OpenFolder openFolder = getOpenFolder(reservation, num, new Direction(reservation.getFrom().getCity(), reservation.getTo().getCity()));
        scheduledTransitionService.returnSeat(reservation.getFrom(), reservation.getTo(), reservation.getSeat());
        reservationService.delete(reservation);
        return createOrUpdateEntity(openFolder);
    }

    public OpenFolder findOpenFolderByTicketNo(TicketNumber ticketNo) {
        return openFolderRepository.findAll().stream()
                .filter(e -> e.getTicketNo().equals(ticketNo))
                .findFirst()
                .orElse(null);
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpired(){
        List<OpenFolder> list = repository.findAll();
        list.stream()
                .filter(e -> e.getExpirationDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList())
                .forEach(s -> {
                    s.setStatus(OpenFolderStatus.DELETED);
                    repository.save(s);
                });
    }


}
