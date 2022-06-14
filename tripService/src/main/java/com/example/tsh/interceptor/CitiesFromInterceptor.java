package com.example.tsh.interceptor;

import com.example.tsh.domain.entity.Transition;
import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.TransitionProperty;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CitiesFromInterceptor implements TripInterceptor {

    private final Predicate<Transition> canGetOn = (transition) ->
            transition.getTransitionOptions().stream()
                        .filter(x -> x.getTransitionProperty() == TransitionProperty.GET_ON)
            .count() == 1;

    public static CitiesFromInterceptor getInstance() {
        return new CitiesFromInterceptor();
    }


    @Override
    public Trip process(Trip trip, String... args) {

        List<Transition> filteredTransitions = trip.getTransitions().stream()
                .filter(canGetOn)
                .collect(Collectors.toList());

        Trip clonedTrip = trip.clone();
        clonedTrip.setTransitions(filteredTransitions);

        return clonedTrip;
    }
}
