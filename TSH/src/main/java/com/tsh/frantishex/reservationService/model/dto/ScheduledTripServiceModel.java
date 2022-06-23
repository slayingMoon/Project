package com.tsh.frantishex.reservationService.model.dto;

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
