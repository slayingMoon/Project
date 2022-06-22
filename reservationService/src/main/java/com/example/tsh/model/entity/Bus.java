package com.example.tsh.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.*;

@Entity
public class Bus extends BaseEntity {
    @Positive
    @Column(nullable = false)
    private Integer seatCapacity;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    List<Driver> drivers;

    public Bus() {
    }

    public Bus(Integer seatsNumber, List<Driver> drivers) {
        this.seatCapacity = seatsNumber;
        this.drivers = drivers;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }


    @Override
    public String toString() {
        return "Bus{" +
                "seatCapacity=" + seatCapacity +
                ", drivers=" + drivers +
                '}';
    }
}
