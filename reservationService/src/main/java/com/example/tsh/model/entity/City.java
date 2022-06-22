package com.example.tsh.model.entity;

import com.example.tsh.model.enums.Country;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"country", "name"}))
public class City extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(nullable = false)
    private String name;

    public City() {
    }

    public City(Country country, String name) {
        this.country = country;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "country=" + country +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
