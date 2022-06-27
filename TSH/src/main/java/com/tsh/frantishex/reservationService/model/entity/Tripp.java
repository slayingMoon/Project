package com.tsh.frantishex.reservationService.model.entity;

import com.tsh.frantishex.tripService.domain.entity.BaseTrip;
import com.tsh.frantishex.tripService.enumeration.DayOfWeek;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "base_trip_id")
public class Tripp extends BaseTripp implements Cloneable {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public com.tsh.frantishex.tripService.domain.entity.Trip clone() {
        try {
            com.tsh.frantishex.tripService.domain.entity.Trip clone = (com.tsh.frantishex.tripService.domain.entity.Trip) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}