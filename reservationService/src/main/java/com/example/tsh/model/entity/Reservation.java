package com.example.tsh.model.entity;

import com.example.tsh.model.enums.ReservationStatus;


import javax.persistence.*;

import javax.validation.constraints.Positive;
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
    private Seat seat;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false)
   private Passenger passenger;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;



    @Column(nullable = false)
    private LocalDateTime reservationDate;

    public Reservation() {

    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }



    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "from=" + from +
                ", to=" + to +
                ", seat=" + seat +
                ", passenger=" + passenger +
                ", reservationStatus=" + reservationStatus +
                ", reservationDate=" + reservationDate +
                '}';
    }

    public Reservation(ScheduledTransition from, ScheduledTransition to, Seat seat, Passenger passenger, ReservationStatus reservationStatus, LocalDateTime reservationDate) {
        this.from = from;
        this.to = to;
        this.seat = seat;
        this.passenger = passenger;
        this.reservationStatus = reservationStatus;
        this.reservationDate = reservationDate;
    }
}
