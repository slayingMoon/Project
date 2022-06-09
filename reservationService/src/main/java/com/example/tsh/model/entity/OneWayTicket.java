package com.example.tsh.model.entity;

import com.example.tsh.model.enums.TicketStatus;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OneWayTicket extends BaseEntity {
   @OneToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(nullable = false,unique = true)
   private TicketNo ticketNo;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Reservation goToReservation;


    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Reservation getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(Reservation goToReservation) {
        this.goToReservation = goToReservation;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public TicketNo getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(TicketNo ticketNo) {
        this.ticketNo = ticketNo;
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

    public OneWayTicket(Reservation goToReservation, TicketStatus status) {
        this.goToReservation = goToReservation;
        this.status = status;
        this.ticketNo = new TicketNo();
    }

    public OneWayTicket() {
    }
}
