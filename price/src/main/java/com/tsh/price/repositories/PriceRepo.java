package com.tsh.price.repositories;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price,Long> {
    Price getPriceByStartCityAndEndCity(City startCity, City endCity);
    void deleteByStartCityAndEndCity(City startCity, City endCity);
}
