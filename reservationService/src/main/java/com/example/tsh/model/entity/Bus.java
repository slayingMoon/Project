package com.example.tsh.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
public class Bus extends BaseEntity {
    @Column(nullable = false)
    private Integer seatCapacity;



    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Driver> drivers;

    public Bus() {
        /// stana le ?
    }

    public Bus(Integer seatsNumber, List<Driver> drivers) {
        this.seatCapacity = seatsNumber;
        this.drivers = drivers;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "seatCapacity=" + seatCapacity +
                ", drivers=" + drivers +
                '}';
    }
}
