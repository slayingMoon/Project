package com.example.tsh.domain.entity;

import com.example.tsh.enumeration.DaysOfWeek;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "base_trip_id")
public class Trip extends BaseTrip {

    @Enumerated(EnumType.STRING)
    private DaysOfWeek daysOfWeek;

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
