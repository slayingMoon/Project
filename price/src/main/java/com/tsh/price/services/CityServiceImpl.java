package com.tsh.price.services;

import com.tsh.price.entities.City;
import com.tsh.price.repositories.CityRepo;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{
    private CityRepo cityRepo;

    public CityServiceImpl(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public City getCity(String name) {
        return  cityRepo.findCityByName(name);
    }

    @Override
    public boolean existsCityByName(String name) {
        return cityRepo.existsByName(name);
    }

    @Override
    public City findCityByName(String name) {
        return cityRepo.findCityByName(name);
    }

    @Override
    public void persist(City city) {
        cityRepo.save(city);
    }


}

