package com.example.tsh.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Seat extends BaseEntity {

    @Column(nullable = false)
    private Integer seatNumber;

    public Seat(Integer seat) {
        this.seatNumber = seat;
    }

    public Seat() {

    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat=" + seatNumber +
                "} " + super.toString();
    }
}
