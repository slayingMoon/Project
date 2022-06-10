package com.example.tsh.model.dto;

public class OneWayTicketServiceModel extends BaseDto {

    private ReservationServiceModel goToReservation;
    private TicketStatus status;

    public ReservationServiceModel getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(ReservationServiceModel goToReservation) {
        this.goToReservation = goToReservation;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
