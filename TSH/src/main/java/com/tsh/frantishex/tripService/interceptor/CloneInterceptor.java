package com.tsh.frantishex.tripService.interceptor;

import com.tsh.frantishex.tripService.domain.entity.Trip;
import org.springframework.beans.BeanUtils;


public class CloneInterceptor implements TripInterceptor{

	public static CloneInterceptor getInstance() {
        return new CloneInterceptor();
    }
	
	@Override
	public Trip process(Trip trip, String... args) {
		return trip.clone();
	}

}
