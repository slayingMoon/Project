package com.tsh.frantishex.price.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PriceCity extends BaseEntity {
    @Column(unique = true)
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public PriceCity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

