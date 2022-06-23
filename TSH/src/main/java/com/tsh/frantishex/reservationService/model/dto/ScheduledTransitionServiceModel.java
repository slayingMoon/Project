package com.tsh.frantishex.reservationService.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduledTransitionServiceModel extends BaseDto {
    private LocalDateTime tripDate;

    private CityServiceModel city;


    private List<SeatServiceModel> seats ;



    public List<SeatServiceModel> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatServiceModel> seats) {
        this.seats = seats;
    }

    public LocalDateTime getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDateTime tripDate) {
        this.tripDate = tripDate;
    }

    public CityServiceModel getCity() {
        return city;
    }

    public void setCity(CityServiceModel city) {
        this.city = city;
    }


}
