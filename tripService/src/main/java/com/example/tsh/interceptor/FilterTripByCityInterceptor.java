package com.example.tsh.interceptor;

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
        String startCity = args[0];
        return this.hasCity.test(trip, startCity) ? trip : null;
    }
}
