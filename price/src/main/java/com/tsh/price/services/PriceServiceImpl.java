package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.entities.Price;
import com.tsh.price.repositories.CityRepo;
import com.tsh.price.repositories.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
@Service
public class PriceServiceImpl implements  PriceService{
    private final CityRepo cityRepo;
    private final PriceRepo priceRepo;
    private final CityService cityService;

    public PriceServiceImpl(CityRepo cityRepo, PriceRepo priceRepo, CityService cityService) {
        this.cityRepo = cityRepo;
        this.priceRepo = priceRepo;
        this.cityService = cityService;
    }

    //work in progress
    @Override
    public List<Price> allPrices() {
        return priceRepo.findAll();
    }

    @Override
    public Price getPrice(String start, String end, boolean isOnline, boolean isDoubleWay) {
        City startCity = cityService.findCityByName(start);
        City endCity = cityService.findCityByName(end);
        return priceRepo.getPriceByStartCityAndEndCity(startCity,endCity);
    }

    @Override
    @Transactional
    public void addPrice(String start, String end, String amount) {
        City startCity;
        City endCity;
        Price price = new Price();
        if(cityService.existsCityByName(start)){
          startCity=cityService.findCityByName(start);
        }else {
            startCity = new City();
            startCity.setName(start);
            cityService.persist(startCity);
        }

        if(cityService.existsCityByName(end)){
            endCity=cityService.findCityByName(end);
        }else {
            endCity = new City();
            endCity.setName(end);
            cityService.persist(endCity);
        }
        price.setStartCity(startCity);
        price.setEndCity(endCity);
        price.setAmount(new BigDecimal(amount));
        price.setDoubleWay(false);
        price.setOnline(false);
        priceRepo.save(price);
    }
    //work in progress

    @Override
    @Transactional
    public void removePrice(String startCity, String endCity) {
          City start = cityService.getCity(startCity);
          City end = cityService.getCity(endCity);
          priceRepo.deleteByStartCityAndEndCity(start,end);

    }
}
