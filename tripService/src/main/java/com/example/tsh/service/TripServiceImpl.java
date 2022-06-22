package com.example.tsh.service;

import com.example.tsh.domain.entity.BaseTrip;
import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.TransitionProperty;
import com.example.tsh.interceptor.*;
import com.example.tsh.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TripServiceImpl {

    @Autowired
    private TripRepository tripRepository;

    private List<Trip> findAll() {
        return this.tripRepository.findAll();
    }


    public Set<String> citiesFrom() {
        List<Trip> allTrips = this.findAll();

        Stream<Trip> processed = allTrips.stream()
        		.map(CloneInterceptor.getInstance()::process)
                .map(TransitionPropertyInterceptor.getInstance(TransitionProperty.GET_ON)::process);

        return collectAllCitiesToSet(processed);
    }


    public Set<String> citiesTo(String startCity) {
        List<Trip> allTrips = this.findAll();

        Stream<Trip> processed = allTrips.stream()
        		.map(CloneInterceptor.getInstance()::process)
                .map(trip -> FilterTripByCityInterceptor.getInstance().process(trip, startCity))
                .filter(Objects::nonNull)
                .map(trip -> RemoveTransitionsBeforeInterceptor.getInstance().process(trip, startCity))
                .map(TransitionPropertyInterceptor.getInstance(TransitionProperty.GET_OFF)::process);
        return collectAllCitiesToSet(processed);
    }



    public Set<String> findAllBuyOnlineCities() {
        List<Trip> allTrips = this.findAll();
        Stream<Trip> processed =  allTrips.stream()
                .map(CloneInterceptor.getInstance()::process)
                .map(TransitionPropertyInterceptor.getInstance(TransitionProperty.GET_ON)::process)
                .map(TransitionPropertyInterceptor.getInstance(TransitionProperty.ONLINE)::process);
        return collectAllCitiesToSet(processed);
    }


    public List<String> findTripsByStartAndDestinationCitiesAndDate(String startCity, String endCity, String date) {
        List<Trip> allTrips = this.findAll();
        return allTrips.stream()
                .map(CloneInterceptor.getInstance()::process)
                .map(trip -> FilterTripByCityInterceptor.getInstance().process(trip, startCity))
                .filter(Objects::nonNull)
                .map(trip -> RemoveTransitionsBeforeInterceptor.getInstance().process(trip, startCity))
                .map(trip -> FilterTripByCityInterceptor.getInstance().process(trip, endCity))
                .filter(Objects::nonNull)
                .map(trip -> TripsByDateInterceptor.getInstance().process(trip, date))
                .filter(Objects::nonNull)

                .map(BaseTrip::getDescription)
                .collect(Collectors.toList());
    }

	private Set<String> collectAllCitiesToSet(Stream<Trip> processed) {
		return processed.flatMap(trip -> trip.getTransitions().stream())
                .map(transition -> transition.getCity().getName())
                .collect(Collectors.toSet());
	}

}
