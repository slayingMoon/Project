package com.example.tsh.model.dto;

import java.util.List;

public class BusServiceModel extends BaseDto{

    private Integer seatCapacity;

    List<DriverServiceModel> drivers;


    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public List<DriverServiceModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverServiceModel> drivers) {
        this.drivers = drivers;
    }



}
