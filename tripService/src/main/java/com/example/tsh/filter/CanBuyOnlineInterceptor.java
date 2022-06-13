package com.example.tsh.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.tsh.domain.entity.Transition;
import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.TransitionProperty;

public class CanBuyOnlineInterceptor implements TripInterceptor{

	private final Predicate<Transition> canBuyOnline = (transition) ->
    transition.getTransitionOptions().stream()
                .filter(x -> x.getTransitionProperty() == TransitionProperty.ONLINE)
    .count() == 1;
	
    public static CanBuyOnlineInterceptor getInstance() {
        return new CanBuyOnlineInterceptor();
    }

	
	@Override
	public Trip process(Trip trip, String...args) {
		List<Transition> filteredTransitions = trip.getTransitions().stream()
                .filter(canBuyOnline)
                .collect(Collectors.toList());

        Trip clonedTrip = trip.clone();
        clonedTrip.setTransitions(filteredTransitions);

        return clonedTrip;
	}

}
