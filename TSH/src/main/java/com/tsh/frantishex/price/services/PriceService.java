package com.tsh.frantishex.price.services;

import com.tsh.frantishex.price.repositories.entities.Price;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PriceService {
    List<Price> allPrices();
    Price getPrice(String startCity,String endCity, boolean isOnline, boolean isDoubleWay);
    void addPrice(String startCity,String endCity, String amount);
    void removePrice(String startCity,String endCity);
}
