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

    public ReservationCity(Country country, String name) {
        this.country = country;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ReservationCity{" +
                "country=" + country +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
