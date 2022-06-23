package com.tsh.frantishex.reservationService.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class ScheduledTransition extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime tripDate;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private ReservationCity reservationCity;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<Seat> seats ;

    public ScheduledTransition() {

    }

    public ScheduledTransition(LocalDateTime tripDate, ReservationCity reservationCity, List<Seat> seats) {
        this.tripDate = tripDate;
        this.reservationCity = reservationCity;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public LocalDateTime getTripDate() {
        return tripDate;
    }

    public ReservationCity getCity() {
        return reservationCity;
    }


    @Override
    public String toString() {
         return String.format("ScheduledTransition: [%s] -> %s %s %s \n", super.getId(), tripDate, reservationCity, seats.size());
    }
}
