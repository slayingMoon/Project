package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;

import java.util.List;

public class PriceServiceImpl implements  PriceService{
    //work in progress
    @Override
    public List<Price> allPrices() {
        return null;
    }
    //work in progress

    @Override
    public Price getPrice(String startCity, String endCity, boolean isOnline, boolean isDoubleWay) {
        return null;
    }

    @Override
    public void addPrice(String start, String end) {
        Price price = new Price();
        City startCity = new City();
        City endCity = new City();
    }
    //work in progress

    @Override
    public void removePrice(String startCity, String endCity) {

    }
}
