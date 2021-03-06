package com.tsh.frantishex.reservationService.model.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OneWayTicket extends BaseEntity {
   @OneToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(nullable = false,unique = true)
   private TicketNumber ticketNumber = new TicketNumber();
    @OneToOne( cascade = {CascadeType.MERGE})
    @JoinColumn(unique = true)
    private Reservation goToReservation;




    public Reservation getGoToReservation() {
        return goToReservation;
    }

    public void setGoToReservation(Reservation goToReservation) {
        this.goToReservation = goToReservation;
    }



    public TicketNumber getTicketNumber() {
        return ticketNumber;
    }


    @Override
    public String toString() {
        return "OneWayTicket{" +
                "goToReservation=" + goToReservation +
                "} " + super.toString();
    }

    public OneWayTicket(Reservation goToReservation) {
        this.goToReservation = goToReservation;
    }

    public OneWayTicket() {
    }
}
