package com.example.tsh.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Direction extends BaseEntity{
    @OneToOne
    @JoinColumn(nullable = false)
    private ScheduledTransition from;

    @OneToOne
    @JoinColumn(nullable = false)
    private ScheduledTransition to;

    public ScheduledTransition getFrom() {
        return from;
    }

    public void setFrom(ScheduledTransition from) {
        this.from = from;
    }

    public ScheduledTransition getTo() {
        return to;
    }

    public void setTo(ScheduledTransition to) {
        this.to = to;
    }
}
