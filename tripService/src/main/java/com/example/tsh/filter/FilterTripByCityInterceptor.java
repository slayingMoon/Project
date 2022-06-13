package com.example.tsh.filter;

import com.example.tsh.domain.entity.Trip;
import java.util.function.BiPredicate;

public class FilterTripByCityInterceptor implements TripInterceptor {

    private final BiPredicate<Trip, String> hasCity = (trip, city) ->
            trip.getTransitions().stream()
                    .anyMatch(transition -> transition.getCity().getName().equals(city));

    public static FilterTripByCityInterceptor getInstance() {
        return new FilterTripByCityInterceptor();
    }


    @Override
    public Trip process(Trip trip, String... args) {
        return this.hasCity.test(trip, args[0]) ? trip : null;
    }
}
