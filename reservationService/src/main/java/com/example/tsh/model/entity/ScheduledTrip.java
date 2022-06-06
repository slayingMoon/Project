package com.example.tsh.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
public class ScheduledTrip extends BaseEntity{
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Bus bus;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ScheduledTransition> scheduledTransitions  ;


    public ScheduledTrip(Bus bus, List<ScheduledTransition> scheduledTransitions) {
        this.bus = bus;
        this.scheduledTransitions =scheduledTransitions;
    }

    public ScheduledTrip() {

    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<ScheduledTransition> getScheduledTransitions() {
      return scheduledTransitions;
    }

    public void setScheduledTransitions(List<ScheduledTransition> scheduledTransitions) {
        this.scheduledTransitions = scheduledTransitions;
    }

    @Override
    public String toString() {
        return "ScheduledTrip{" +
                "bus=" + bus +
                ", scheduledTransitions=" + scheduledTransitions +
                "} " + super.toString();
    }
}
