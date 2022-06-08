package com.example.tsh.service;

import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.DaysOfWeek;
import com.example.tsh.enumeration.TransitionProperties;
import com.example.tsh.repository.EmergencyTripRepository;
import com.example.tsh.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
	private static final int DAYS_IN_WEEK = DaysOfWeek.values().length;

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

	@Override
	public List<String> generateTripDates(Long tripID) {
		Trip trip = tripRepository.findById(tripID).get();
		LocalDate startDate = LocalDate.now();
		int tripDayAsNum = trip.getDaysOfWeek().ordinal();
		int currentDayAsNum = startDate.getDayOfWeek().ordinal();
		int difference = tripDayAsNum - currentDayAsNum >= 0 ? 
				tripDayAsNum - currentDayAsNum :
					tripDayAsNum - currentDayAsNum + DAYS_IN_WEEK;
		startDate = startDate.plusDays(difference);
		LocalDate finalDate = startDate.plusYears(1);
		List<String> dates = new ArrayList<>();
		while(startDate.isBefore(finalDate)) {
			dates.add(startDate.toString());
			startDate = startDate.plusDays(DAYS_IN_WEEK);
		}
		return dates;
	}



}
