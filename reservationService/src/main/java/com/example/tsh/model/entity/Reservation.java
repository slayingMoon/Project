package com.example.tsh.model.entity;

import com.example.tsh.model.enums.ReservationConfirmed;
import com.example.tsh.model.enums.ReservationPaid;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    @OneToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private ScheduledTransition from;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private ScheduledTransition to;

    @OneToOne(cascade = { CascadeType.MERGE})
    private ScheduledTrip scheduledTrip;

    @OneToOne(cascade = { CascadeType.MERGE})
    private Seat seat;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationPaid isPaid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationConfirmed isConfirmed;



    @Column(nullable = false)
    private LocalDateTime reservationDate;




    public Reservation() {

    }

    public Reservation(ScheduledTransition from, ScheduledTransition to, ScheduledTrip scheduledTrip, Seat seat, String firstName,
                       String lastName, ReservationPaid isPaid, ReservationConfirmed isConfirmed, LocalDateTime reservationDate) {
        this.from = from;
        this.to = to;
        this.scheduledTrip = scheduledTrip;
        this.seat = seat;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPaid = isPaid;
        this.isConfirmed = isConfirmed;
        this.reservationDate = reservationDate;

    }



    public ScheduledTrip getScheduledTrip() {
        return scheduledTrip;
    }

    public void setScheduledTrip(ScheduledTrip scheduledTrip) {
        this.scheduledTrip = scheduledTrip;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public ReservationPaid getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(ReservationPaid isPaid) {
        this.isPaid = isPaid;
    }

    public ReservationConfirmed getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(ReservationConfirmed isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ScheduledTransition getFrom() {
        return from;
    }

    public void setFrom(ScheduledTransition from) {
        this.from = from;
    }

    public ScheduledTransition getTo() {
        return to;
    }

    public void setTo(ScheduledTransition to) {
        this.to = to;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "from=" + from +
                ", to=" + to +
                ", seatNum=" + seat +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
