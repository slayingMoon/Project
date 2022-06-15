package com.tsh.price.services;

import com.tsh.price.entities.City;

public interface CityService {
    City getCity(String name);

    boolean existsCityByName(String start);

    City findCityByName(String start);

    void persist(City startCity);
}
