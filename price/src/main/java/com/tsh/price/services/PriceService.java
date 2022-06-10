package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;

import java.util.List;

public interface PriceService {
    List<Price> allPrices();
    Price getPrice(String startCity,String endCity, boolean isOnline, boolean isDoubleWay);
    void addPrice(String startCity,String endCity);
    void removePrice(String startCity,String endCity);
}
