package com.example.tsh.filter;

import com.example.tsh.domain.entity.Trip;

public interface TripInterceptor {

    /*
    TODO: write doc
     */
    Trip process(Trip trip, String... args);




}
