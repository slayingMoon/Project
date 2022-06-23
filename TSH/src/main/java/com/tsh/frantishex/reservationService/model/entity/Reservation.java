package com.tsh.frantishex.reservationService.model.entity;

import com.tsh.frantishex.reservationService.model.enums.ReservationStatus;


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

    public Reservation(ReservationBuilder reservationBuilder) {
        this.from = reservationBuilder.from;
        this.to = reservationBuilder.to;
        this.seat = reservationBuilder.seat;
        this.passenger = reservationBuilder.passenger;
        this.reservationStatus = reservationBuilder.reservationStatus;
        this.reservationDate = reservationBuilder.reservationDate;
    }
    public static class ReservationBuilder{
        private ScheduledTransition from;
        private ScheduledTransition to;
        private Seat seat;
        private Passenger passenger;
        private ReservationStatus reservationStatus;
        private LocalDateTime reservationDate;

        public ReservationBuilder from(ScheduledTransition from) {
            this.from = from;
            return this;
        }

        public ReservationBuilder to(ScheduledTransition to) {
            this.to = to;
            return this;
        }

        public ReservationBuilder seat(Seat seat) {
            this.seat = seat;
            return this;
        }

        public ReservationBuilder passenger(Passenger passenger) {
            this.passenger = passenger;
            return this;
        }

        public ReservationBuilder reservationStatus(ReservationStatus reservationStatus) {
            this.reservationStatus = reservationStatus;
            return this;
        }

        public ReservationBuilder reservationDate(LocalDateTime reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }
        public Reservation build(){
            return new Reservation(this);
        }
    }
}
