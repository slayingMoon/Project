package com.tsh.frantishex.price.services;

import com.tsh.frantishex.price.repositories.CityRepo;
import com.tsh.frantishex.price.repositories.entities.PriceCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{
   @Autowired
    private CityRepo cityRepo;



    @Override
    public PriceCity getCity(String name) {
        return  cityRepo.findPriceCityByName(name);
    }

    @Override
    public boolean existsCityByName(String name) {
        return cityRepo.existsByName(name);
    }

    @Override
    public PriceCity findCityByName(String name) {
        return cityRepo.findPriceCityByName(name);
    }

    @Override
    public void persist(PriceCity priceCity) {
        cityRepo.save(priceCity);
    }


}

