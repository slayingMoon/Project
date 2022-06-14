package com.example.tsh.interceptor;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.tsh.domain.entity.Transition;
import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.TransitionProperty;

public class CitiesToInterceptor implements TripInterceptor{

	private final Predicate<Transition> canGetOff = (transition) ->
    transition.getTransitionOptions().stream()
                .filter(x -> x.getTransitionProperty() == TransitionProperty.GET_OFF)
    .count() == 1;
	
    public static CitiesToInterceptor getInstance() {
        return new CitiesToInterceptor();
    }
    
	@Override
	public Trip process(Trip trip, String... args) {
		List<Transition> filteredTransitions = trip.getTransitions().stream()
                .filter(canGetOff)
                .collect(Collectors.toList());

        Trip clonedTrip = trip.clone();
        clonedTrip.setTransitions(filteredTransitions);

        return clonedTrip;
	}

}
