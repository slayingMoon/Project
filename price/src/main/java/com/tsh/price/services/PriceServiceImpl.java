package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;
import com.tsh.price.repositories.CityRepo;
import com.tsh.price.repositories.PriceRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
@Service
public class PriceServiceImpl implements  PriceService{
    private CityRepo cityRepo;
    private PriceRepo priceRepo;

    public PriceServiceImpl(CityRepo cityRepo, PriceRepo priceRepo) {
        this.cityRepo = cityRepo;
        this.priceRepo = priceRepo;
    }

    //work in progress
    @Override
    public List<Price> allPrices() {
        return priceRepo.findAll();
    }

    @Override
    public Price getPrice(String startCity, String endCity, boolean isOnline, boolean isDoubleWay) {
        return null;
    }

    @Override
    @Transactional
    public void addPrice(String start, String end, String amount) {
        Price price = new Price();
        City startCity = new City();
        City endCity = new City();
        startCity.setName(start);
        endCity.setName(end);
        price.setStartCity(startCity);
        price.setEndCity(endCity);
        cityRepo.save(startCity);
        cityRepo.save(endCity);
        price.setAmount(new BigDecimal(amount));
        price.setDoubleWay(false);
        price.setOnline(false);
        priceRepo.save(price);
    }
    //work in progress

    @Override
    @Transactional
    public void removePrice(String startCity, String endCity) {
        
    }
}
