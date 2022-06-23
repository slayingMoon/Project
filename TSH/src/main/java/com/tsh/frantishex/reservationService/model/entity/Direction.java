package com.tsh.frantishex.reservationService.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Direction extends BaseEntity{
    @OneToOne
    @JoinColumn(nullable = false)
    private ReservationCity from;

    @OneToOne
    @JoinColumn(nullable = false)
    private ReservationCity to;

    public Direction() {

    }

    public ReservationCity getFrom() {
        return from;
    }


    public ReservationCity getTo() {
        return to;
    }



    public Direction(ReservationCity from, ReservationCity to) {
        this.from = from;
        this.to = to;
    }
}
