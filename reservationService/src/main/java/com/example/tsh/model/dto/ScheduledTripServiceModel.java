package com.example.tsh.model.dto;

import com.example.tsh.model.entity.Bus;
import com.example.tsh.model.entity.ScheduledTransition;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

public class ScheduledTripServiceModel extends BaseDto{


    private BusServiceModel bus;


    private List<ScheduledTransitionServiceModel> scheduledTransitions  ;




    public BusServiceModel getBus() {
        return bus;
    }

    public void setBus(BusServiceModel bus) {
        this.bus = bus;
    }

    public List<ScheduledTransitionServiceModel> getScheduledTransitions() {
        return scheduledTransitions;
    }

    public void setScheduledTransitions(List<ScheduledTransitionServiceModel> scheduledTransitions) {
        this.scheduledTransitions = scheduledTransitions;
    }


}
