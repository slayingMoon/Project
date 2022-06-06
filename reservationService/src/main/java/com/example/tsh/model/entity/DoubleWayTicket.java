package com.example.tsh.model.entity;

import javax.persistence.*;

@Entity

public class DoubleWayTicket extends BaseEntity{

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private OneWayTicket firstTicket;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private OneWayTicket secondTicket;

    public OneWayTicket getFirstTicket() {
        return firstTicket;
    }

    public void setFirstTicket(OneWayTicket firstTicket) {
        this.firstTicket = firstTicket;
    }

    public OneWayTicket getSecondTicket() {
        return secondTicket;
    }

    public void setSecondTicket(OneWayTicket secondTicket) {
        this.secondTicket = secondTicket;
    }

    @Override
    public String toString() {
        return "DoubleWayTicket{" +
                "firstTicket=" + firstTicket +
                ", secondTicket=" + secondTicket +
                "} " + super.toString();
    }
}
