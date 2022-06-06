package com.example.tsh.model.dto;

import com.example.tsh.model.enums.Country;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class CityServiceModel extends BaseDto {



    private Country country;
    private String name;



    public Country getCountry() {
        return country;
    }


    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
