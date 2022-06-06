package com.example.tsh.domain.entity;

import javax.persistence.Entity;

@Entity
public class City extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }
}
