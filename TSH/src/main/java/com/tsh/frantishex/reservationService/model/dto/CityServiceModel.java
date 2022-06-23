package com.tsh.frantishex.reservationService.model.dto;

import com.tsh.frantishex.reservationService.model.enums.Country;

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
