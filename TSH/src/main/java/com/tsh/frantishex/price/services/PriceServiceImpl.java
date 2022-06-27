package com.tsh.frantishex.price.services;

import com.tsh.frantishex.price.repositories.CityRepo;
import com.tsh.frantishex.price.repositories.PriceRepo;
import com.tsh.frantishex.price.entities.PriceCity;
import com.tsh.frantishex.price.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service
public class PriceServiceImpl implements  PriceService{
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private  PriceRepo priceRepo;
    @Autowired
    private CityService cityService;



    //work in progress
    @Override
    public List<Price> allPrices() {
        return priceRepo.findAll();
    }

    @Override
    public Price getPrice(String start, String end, boolean isOnline, boolean isDoubleWay) {
        PriceCity startPriceCity = cityService.findCityByName(start);
        PriceCity endPriceCity = cityService.findCityByName(end);
        return priceRepo.getPriceByStartPriceCityAndEndPriceCity(startPriceCity, endPriceCity);
    }

    @Override
    @Transactional
    public void addPrice(String start, String end, String amount) {
        PriceCity startPriceCity;
        PriceCity endPriceCity;
        Price price = new Price();
        if(cityService.existsCityByName(start)){
          startPriceCity =cityService.findCityByName(start);
        }else {
            startPriceCity = new PriceCity();
            startPriceCity.setName(start);
            cityService.persist(startPriceCity);
        }

        if(cityService.existsCityByName(end)){
            endPriceCity =cityService.findCityByName(end);
        }else {
            endPriceCity = new PriceCity();
            endPriceCity.setName(end);
            cityService.persist(endPriceCity);
        }
        price.setStartCity(startPriceCity);
        price.setEndCity(endPriceCity);
        price.setAmount(new BigDecimal(amount));
        price.setDoubleWay(false);
        price.setOnline(false);
        priceRepo.save(price);
    }
    //work in progress

    @Override
    @Transactional
    public void removePrice(String startCity, String endCity) {
          PriceCity start = cityService.getCity(startCity);
          PriceCity end = cityService.getCity(endCity);
          priceRepo.deleteByStartPriceCityAndEndPriceCity(start,end);

    }
}
