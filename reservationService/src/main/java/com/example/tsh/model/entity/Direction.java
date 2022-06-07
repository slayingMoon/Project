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

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }
}
