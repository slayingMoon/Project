package com.example.tsh.model.dto;

import com.example.tsh.model.enums.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationServiceModel extends BaseDto {

    private ScheduledTransitionServiceModel from;


    private ScheduledTransitionServiceModel to;

    private ScheduledTripServiceModel scheduledTrip;

    private SeatServiceModel seat;

    private String firstName;

    private String lastName;


    private ReservationStatus isConfirmed;

    private LocalDateTime reservationDate;

    public ReservationServiceModel() {
    }







    public ReservationServiceModel(ScheduledTransitionServiceModel from, ScheduledTransitionServiceModel to, ScheduledTripServiceModel scheduledTrip, SeatServiceModel seat, String firstName, String lastName, ReservationStatus isConfirmed, LocalDateTime reservationDate) {
        this.from = from;
        this.to = to;
        this.scheduledTrip = scheduledTrip;
        this.seat = seat;
        this.firstName = firstName;
        this.lastName = lastName;

        this.isConfirmed = isConfirmed;
        this.reservationDate = reservationDate;
    }

    public ScheduledTransitionServiceModel getFrom() {
        return from;
    }

    public void setFrom(ScheduledTransitionServiceModel from) {
        this.from = from;
    }

    public ScheduledTransitionServiceModel getTo() {
        return to;
    }

    public void setTo(ScheduledTransitionServiceModel to) {
        this.to = to;
    }

    public ScheduledTripServiceModel getScheduledTrip() {
        return scheduledTrip;
    }

    public void setScheduledTrip(ScheduledTripServiceModel scheduledTrip) {
        this.scheduledTrip = scheduledTrip;
    }

    public SeatServiceModel getSeat() {
        return seat;
    }

    public void setSeat(SeatServiceModel seat) {
        this.seat = seat;
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



    public ReservationStatus getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(ReservationStatus isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}
