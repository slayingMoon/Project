package com.tsh.frantishex.price.repositories;


import com.tsh.frantishex.price.entities.PriceCity;
import com.tsh.frantishex.price.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price,Long> {
    Price getPriceByStartPriceCityAndEndPriceCity(PriceCity startPriceCity, PriceCity endPriceCity);
    void deleteByStartPriceCityAndEndPriceCity(PriceCity startPriceCity, PriceCity endPriceCity);
}
