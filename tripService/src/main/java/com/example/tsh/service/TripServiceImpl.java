package com.example.tsh.service;

import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.DayOfWeek;
import com.example.tsh.enumeration.TransitionProperty;
import com.example.tsh.interceptor.CitiesFromInterceptor;
import com.example.tsh.interceptor.CitiesToInterceptor;
import com.example.tsh.interceptor.RemoveTransitionsBeforeInterceptor;
import com.example.tsh.interceptor.FilterTripByCityInterceptor;
import com.example.tsh.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private static final int DAYS_IN_WEEK = DayOfWeek.values().length;

    @Autowired
    private TripRepository tripRepository;

    private List<Trip> findAll() {
        return this.tripRepository.findAll();
    }

    @Override
    public Set<String> citiesFrom() {
        List<Trip> allTrips = this.findAll();

        Set<String> processed = allTrips.stream()
                .map(CitiesFromInterceptor.getInstance()::process)

                .flatMap(trip -> trip.getTransitions().stream())
                .map(transition -> transition.getCity().getName())
                .collect(Collectors.toSet());

        return processed;
    }

    @Override
    public Set<String> citiesTo(String startCity) {
        List<Trip> allTrips = this.findAll();

        Set<String> processed = allTrips.stream()
                .map(trip -> FilterTripByCityInterceptor.getInstance().process(trip, startCity))
                .filter(Objects::nonNull)
                .map(trip -> RemoveTransitionsBeforeInterceptor.getInstance().process(trip, startCity))
                .map(CitiesToInterceptor.getInstance()::process)

                .flatMap(trip -> trip.getTransitions().stream())
                .map(transition -> transition.getCity().getName())
                .collect(Collectors.toSet());


        return processed;
    }

    @Override
    public List<String> findAllBuyOnlineCities() {
        List<Trip> allTrips = this.findAll();
        return allTrips.stream()
                .flatMap(t -> t.getTransitions().stream())
                .filter(transition -> transition.getTransitionOptions().stream()
                        .anyMatch(detail -> detail.getTransitionProperty() ==
                                TransitionProperty.ONLINE))
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
                        if (transition.getCity().getName().equals(startCity) && transition.getTransitionOptions().stream()
                                .anyMatch(detail -> detail.getTransitionProperty() ==
                                        TransitionProperty.GET_ON)) {
                            isStartCityPassed.set(true);
                        }

                        if (transition.getTransitionOptions().stream()
                                .anyMatch(detail -> detail.getTransitionProperty() ==
                                        TransitionProperty.GET_OFF && transition.getCity().getName().equals(endCity)) && isStartCityPassed.get()) {
                            allPossibleTrips.add(trip.getDescription());
                        }

                    });
        });
        return allPossibleTrips;
    }

    @Override
    public List<String> generateTripDates(Long tripID) {
        Trip trip = tripRepository.findById(tripID).get();
        LocalDate startDate = LocalDate.now();
        int tripDayAsNum = trip.getDayOfWeek().ordinal();
        int currentDayAsNum = startDate.getDayOfWeek().ordinal();
        int difference = tripDayAsNum - currentDayAsNum >= 0 ?
                tripDayAsNum - currentDayAsNum :
                tripDayAsNum - currentDayAsNum + DAYS_IN_WEEK;
        startDate = startDate.plusDays(difference);
        LocalDate finalDate = startDate.plusYears(1);
        List<String> dates = new ArrayList<>();
        while (startDate.isBefore(finalDate)) {
            dates.add(startDate.toString());
            startDate = startDate.plusDays(DAYS_IN_WEEK);
        }
        return dates;
    }


}
