package com.tsh.frantishex.tripService.domain.entity;

import com.tsh.frantishex.tripService.enumeration.DayOfWeek;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "base_trip_id")
public class Trip extends BaseTrip implements Cloneable {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Trip clone() {
        try {
            Trip clone = (Trip) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
