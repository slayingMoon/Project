package com.example.tsh.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Driver extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String phone1;
    @Column(nullable = false,unique = true)
    private String phone2;

    public Driver(String name, String phone1, String phone2) {
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }
}