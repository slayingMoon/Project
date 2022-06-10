package com.example.tsh.model.entity;

import com.example.tsh.model.enums.ReservationStatus;

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
    private ReservationStatus reservationStatus;



    @Column(nullable = false)
    private LocalDateTime reservationDate;




    public Reservation() {

    }

    public Reservation(ScheduledTransition from, ScheduledTransition to, ScheduledTrip scheduledTrip, Seat seat, String firstName,
                       String lastName, ReservationStatus isConfirmed, LocalDateTime reservationDate) {
        this.from = from;
        this.to = to;
        this.scheduledTrip = scheduledTrip;
        this.seat = seat;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationStatus = isConfirmed;
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
