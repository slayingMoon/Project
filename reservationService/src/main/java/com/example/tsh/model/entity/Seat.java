package com.example.tsh.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.*;

@Entity
public class Seat extends BaseEntity {

    @Column(nullable = false)
    private Integer seat;

    public Seat(Integer seat) {
        this.seat = seat;
    }

    public Seat() {

    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat=" + seat +
                "} " + super.toString();
    }
}
