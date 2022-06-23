package com.tsh.frantishex.reservationService.model.entity;

import javax.persistence.*;

@Entity

public class DoubleWayTicket extends OneWayTicket{

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn
    private Reservation returnReservation;

    public Reservation getReturnReservation() {
        return returnReservation;
    }


    public void setReturnReservation(Reservation returnReservation) {
        this.returnReservation = returnReservation;
    }
}
