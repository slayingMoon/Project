package com.tsh.frantishex.tripService.domain.entity;

import javax.persistence.Entity;

@Entity
public class TripCity extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }
}
