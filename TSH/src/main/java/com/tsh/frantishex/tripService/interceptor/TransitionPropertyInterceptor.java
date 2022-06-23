package com.tsh.frantishex.tripService.interceptor;

import com.tsh.frantishex.tripService.domain.entity.Transition;
import com.tsh.frantishex.tripService.domain.entity.Trip;
import com.tsh.frantishex.tripService.enumeration.TransitionProperty;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TransitionPropertyInterceptor implements TripInterceptor {

	private TransitionProperty transitionProperty;

	private final Predicate<Transition> filterByTransitionProperty = (transition) ->
    transition.getTransitionOptions().stream()
			.anyMatch(x -> x.getTransitionProperty() == transitionProperty);
	
    public static TransitionPropertyInterceptor getInstance(TransitionProperty transitionProperty) {
        return new TransitionPropertyInterceptor(transitionProperty);
    }

	private TransitionPropertyInterceptor(TransitionProperty transitionProperty) {
		this.transitionProperty = transitionProperty;
	}



	@Override
	public Trip process(Trip trip, String... args) {
		List<Transition> filteredTransitions = trip.getTransitions().stream()
                .filter(filterByTransitionProperty)
                .collect(Collectors.toList());

		trip.setTransitions(filteredTransitions);
        return trip;
	}

}
