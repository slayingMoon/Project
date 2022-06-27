package com.tsh.frantishex.reservationService.model.entity;

import com.tsh.frantishex.reservationService.model.enums.Country;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"country", "name"}))
public class ReservationCity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(nullable = false)
    private String name;

    public ReservationCity() {
    }

    public ReservationCity( String name) {
        this.country = Country.DEFAULT;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReservationCity{" +
                "country=" + country +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
