package com.example.tsh.model.entity;

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




    public Reservation getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(Reservation goToReservation) {
        this.goToReservation = goToReservation;
    }



    public TicketNo getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(TicketNo ticketNo) {
        this.ticketNo = ticketNo;
    }



    @Override
    public String toString() {
        return "OneWayTicket{" +
                "goToReservation=" + goToReservation +
                "} " + super.toString();
    }

    public OneWayTicket(Reservation goToReservation) {
        this.goToReservation = goToReservation;
        this.ticketNo = new TicketNo();
    }

    public OneWayTicket() {
    }
}
