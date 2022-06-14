package com.example.tsh.interceptor;

import com.example.tsh.domain.entity.Trip;

public interface TripInterceptor {

    /*
    TODO: write doc
     */
    Trip process(Trip trip, String... args);




}
