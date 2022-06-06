package com.example.tsh.model.dto;

import com.example.tsh.model.enums.ReservationConfirmed;
import com.example.tsh.model.enums.ReservationDirections;
import com.example.tsh.model.enums.ReservationPaid;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReservationServiceModel extends BaseDto {

    private ScheduledTransitionServiceModel from;


    private ScheduledTransitionServiceModel to;

    private ScheduledTripServiceModel scheduledTrip;

    private SeatServiceModel seat;

    private String firstName;

    private String lastName;

    private ReservationPaid isPaid;

    private ReservationConfirmed isConfirmed;

    private LocalDateTime reservationDate;
    private ReservationDirections reservationDirections;
    public ReservationServiceModel() {
    }



    public ReservationDirections getReservationDirections() {
        return reservationDirections;
    }

    public void setReservationDirections(ReservationDirections reservationDirections) {
        this.reservationDirections = reservationDirections;
    }

    public ReservationServiceModel(ScheduledTransitionServiceModel from, ScheduledTransitionServiceModel to, ScheduledTripServiceModel scheduledTrip, SeatServiceModel seat, String firstName, String lastName, ReservationPaid isPaid, ReservationConfirmed isConfirmed, LocalDateTime reservationDate, ReservationDirections reservationDirections) {
        this.from = from;
        this.to = to;
        this.scheduledTrip = scheduledTrip;
        this.seat = seat;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPaid = isPaid;
        this.isConfirmed = isConfirmed;
        this.reservationDate = reservationDate;
        this.reservationDirections = reservationDirections;
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
}
