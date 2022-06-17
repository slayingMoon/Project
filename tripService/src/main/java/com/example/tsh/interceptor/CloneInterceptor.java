package com.example.tsh.interceptor;

import org.springframework.beans.BeanUtils;

import com.example.tsh.domain.entity.Trip;

public class CloneInterceptor implements TripInterceptor{

	public static CloneInterceptor getInstance() {
        return new CloneInterceptor();
    }
	
	@Override
	public Trip process(Trip trip, String... args) {
		Trip clonedTrip = trip.clone();
		return clonedTrip;
	}

}
