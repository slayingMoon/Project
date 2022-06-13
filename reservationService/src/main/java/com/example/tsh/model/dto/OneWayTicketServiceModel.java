package com.example.tsh.model.dto;

public class OneWayTicketServiceModel extends BaseDto {

    private ReservationServiceModel goToReservation;


    public ReservationServiceModel getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(ReservationServiceModel goToReservation) {
        this.goToReservation = goToReservation;
    }


}
