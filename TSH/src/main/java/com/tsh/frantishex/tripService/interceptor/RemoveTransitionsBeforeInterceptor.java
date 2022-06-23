package com.tsh.frantishex.tripService.interceptor;

import com.tsh.frantishex.tripService.domain.entity.Transition;
import com.tsh.frantishex.tripService.domain.entity.Trip;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class RemoveTransitionsBeforeInterceptor implements TripInterceptor {

    private final BiPredicate<Transition, Long> isBeforeCity = (transition, id) ->
            transition.getId() >= id;

    public static RemoveTransitionsBeforeInterceptor getInstance() {
        return new RemoveTransitionsBeforeInterceptor();
    }

    @Override
    public Trip process(Trip trip, String... args) {
        String startCity = args[0];

        Transition startCityTransition = trip.getTransitions().stream()
                .filter(tr-> tr.getCity().getName()
                .equals(startCity)).findFirst().get();

        List<Transition> filteredTransitions = trip.getTransitions().stream()
                .filter(tr -> isBeforeCity.test(tr, startCityTransition.getId()))
                .collect(Collectors.toList());

        Trip clonedTrip = trip.clone();
        clonedTrip.setTransitions(filteredTransitions);
        return clonedTrip;
    }
}
