package com.example.tsh.service;

import com.example.tsh.domain.entity.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    List<String> findAllStartingCities();

    List<String> findAllPossibleDestinations(String startCity);

    List<String> findAllBuyOnlineCities();

    List<String> findTripsByStartAndDestinationCities(String startCity, String endCity);

}
