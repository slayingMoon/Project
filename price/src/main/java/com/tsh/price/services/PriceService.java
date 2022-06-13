package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PriceService {
    List<Price> allPrices();
    Price getPrice(String startCity,String endCity, boolean isOnline, boolean isDoubleWay);
    void addPrice(String startCity,String endCity, String amount);
    void removePrice(String startCity,String endCity);
}
