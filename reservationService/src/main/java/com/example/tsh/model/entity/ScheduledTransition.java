package com.example.tsh.model.entity;

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
    private City city;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<Seat> seats ;

    public ScheduledTransition() {

    }

    public ScheduledTransition(LocalDateTime tripDate, City city, List<Seat> seats) {
        this.tripDate = tripDate;
        this.city = city;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public LocalDateTime getTripDate() {
        return tripDate;
    }

    public City getCity() {
        return city;
    }


    @Override
    public String toString() {
         return String.format("ScheduledTransition: [%s] -> %s %s %s \n", super.getId(), tripDate, city, seats.size());
    }
}
