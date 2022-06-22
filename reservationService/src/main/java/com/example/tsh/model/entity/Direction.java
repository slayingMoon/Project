package com.example.tsh.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Direction extends BaseEntity{
    @OneToOne
    @JoinColumn(nullable = false)
    private City from;

    @OneToOne
    @JoinColumn(nullable = false)
    private City to;

    public Direction() {

    }

    public City getFrom() {
        return from;
    }


    public City getTo() {
        return to;
    }



    public Direction(City from, City to) {
        this.from = from;
        this.to = to;
    }
}
