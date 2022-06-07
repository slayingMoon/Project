package com.example.tsh.model.entity;

import com.example.tsh.model.enums.TicketStatus;

import javax.persistence.*;

@Entity
public class OneWayTicket extends BaseEntity  {

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(nullable = false)
    protected Reservation goToReservation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected TicketStatus status;

    public Reservation getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(Reservation goToReservation) {
        this.goToReservation = goToReservation;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OneWayTicket{" +
                "goToReservation=" + goToReservation +
                ", status=" + status +
                "} " + super.toString();
    }
}
