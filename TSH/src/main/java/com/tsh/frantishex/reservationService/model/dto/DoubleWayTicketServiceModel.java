package com.tsh.frantishex.reservationService.model.dto;

public class DoubleWayTicketServiceModel extends BaseDto {

    private OneWayTicketServiceModel firstTicket;

    private OneWayTicketServiceModel secondTicket;

    public OneWayTicketServiceModel getFirstTicket() {
        return firstTicket;
    }

    public void setFirstTicket(OneWayTicketServiceModel firstTicket) {
        this.firstTicket = firstTicket;
    }

    public OneWayTicketServiceModel getSecondTicket() {
        return secondTicket;
    }

    public void setSecondTicket(OneWayTicketServiceModel secondTicket) {
        this.secondTicket = secondTicket;
    }
}
