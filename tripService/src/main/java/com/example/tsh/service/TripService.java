package com.example.tsh.service;

import java.util.List;
import java.util.Set;

public interface TripService {

    Set<String> citiesFrom();

    Set<String> citiesTo(String startCity);

    List<String> findAllBuyOnlineCities();

    List<String> findTripsByStartAndDestinationCities(String startCity, String endCity);
    
    List<String> generateTripDates(Long tripID);

}
