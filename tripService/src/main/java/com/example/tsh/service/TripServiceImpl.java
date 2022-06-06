package com.example.tsh.service;

import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.TransitionProperties;
import com.example.tsh.repository.EmergencyTripRepository;
import com.example.tsh.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private EmergencyTripRepository emergencyTripRepository;


    @Override
    public List<Trip> findAll() {
        return this.tripRepository.findAll();
    }

    @Override
    public List<String> findAllStartingCities() {
        List<Trip> allTrips = this.findAll();
        return allTrips.stream()
                .flatMap(t -> t.getTransitions().stream())
                .filter(transition -> transition.getTransitionDetails().stream()
                        .anyMatch(detail -> detail.getTransitionProperties() ==
                                            TransitionProperties.GET_ON))
                .map(transition -> transition.getCity().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllPossibleDestinations(String startCity) {
        List<Trip> allTrips = this.findAll();
        Set<String> allPossibleDestinationCities = new LinkedHashSet<>();
        allTrips.forEach(trip -> {
            AtomicBoolean isStartCityPassed = new AtomicBoolean(false);
            trip.getTransitions()
                    .forEach(transition -> {
                        if (transition.getCity().getName().equals(startCity) && transition.getTransitionDetails().stream()
                                .anyMatch(detail -> detail.getTransitionProperties() ==
                                TransitionProperties.GET_ON)) {
                            isStartCityPassed.set(true);
                        }

                        if (transition.getTransitionDetails().stream()
                                .anyMatch(detail -> detail.getTransitionProperties() ==
                                        TransitionProperties.GET_OFF) && isStartCityPassed.get()) {
                            allPossibleDestinationCities.add(transition.getCity().getName());
                        }

                    });
        });

        return new ArrayList<>(allPossibleDestinationCities);
    }

    @Override
    public List<String> findAllBuyOnlineCities() {
        List<Trip> allTrips = this.findAll();
        return allTrips.stream()
                .flatMap(t -> t.getTransitions().stream())
                .filter(transition -> transition.getTransitionDetails().stream()
                        .anyMatch(detail -> detail.getTransitionProperties() ==
                                TransitionProperties.ONLINE))
                .map(transition -> transition.getCity().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findTripsByStartAndDestinationCities(String startCity, String endCity) {
        List<Trip> allTrips = this.findAll();
        List<String> allPossibleTrips = new ArrayList<>();
        allTrips.forEach(trip -> {
            AtomicBoolean isStartCityPassed = new AtomicBoolean(false);
            trip.getTransitions()
                    .forEach(transition -> {
                        if (transition.getCity().getName().equals(startCity) && transition.getTransitionDetails().stream()
                                .anyMatch(detail -> detail.getTransitionProperties() ==
                                        TransitionProperties.GET_ON)) {
                            isStartCityPassed.set(true);
                        }

                        if (transition.getTransitionDetails().stream()
                                .anyMatch(detail -> detail.getTransitionProperties() ==
                                        TransitionProperties.GET_OFF && transition.getCity().getName().equals(endCity)) && isStartCityPassed.get()) {
                            allPossibleTrips.add(trip.getDescription());
                        }

                    });
        });
        return allPossibleTrips;
    }



}
