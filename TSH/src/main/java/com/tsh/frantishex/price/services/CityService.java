package com.tsh.frantishex.price.services;


import com.tsh.frantishex.price.repositories.entities.PriceCity;

public interface CityService {
    PriceCity getCity(String name);

    boolean existsCityByName(String start);

    PriceCity findCityByName(String start);

    void persist(PriceCity startPriceCity);
}
