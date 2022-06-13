package com.example.tsh.service;

import java.util.List;

public interface TripService {

    List<String> citiesFrom();

    List<String> citiesTo(String startCity);

    List<String> findAllBuyOnlineCities();

    List<String> findTripsByStartAndDestinationCities(String startCity, String endCity);
    
    List<String> generateTripDates(Long tripID);

}
