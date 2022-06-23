package com.tsh.frantishex.tripService.interceptor;


import com.tsh.frantishex.tripService.domain.entity.Trip;

public interface TripInterceptor {

    /*
    TODO: write doc
     */
    Trip process(Trip trip, String... args);




}
