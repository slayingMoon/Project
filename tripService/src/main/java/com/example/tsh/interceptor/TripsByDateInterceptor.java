package com.example.tsh.interceptor;

import com.example.tsh.domain.entity.Trip;
import com.example.tsh.enumeration.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TripsByDateInterceptor implements TripInterceptor {

    private static final int DAYS_IN_WEEK = DayOfWeek.values().length;

    private final BiPredicate<List<LocalDate>, LocalDate> containsDate = (tripDates, dateArg) ->
            tripDates.stream().anyMatch(dateArg::isEqual);

    private final Predicate<LocalTime> isAfter = (tripTime) ->
            tripTime.isAfter(LocalTime.now().plusHours(1));


    public static TripsByDateInterceptor getInstance() {
        return new TripsByDateInterceptor();
    }

    @Override
    public Trip process(Trip trip, String... args) {
        LocalDate dateArg = LocalDate.parse(args[0]);
        List<LocalDate> generatedDates = generateTripDates(trip);
        return this.containsDate.test(generatedDates, dateArg) &&
                this.isAfter.test(trip.getDepartureTime()) ? trip : null;
    }

    private List<LocalDate> generateTripDates(Trip trip) {
        LocalDate startDate = LocalDate.now();
        int tripDayAsNum = trip.getDayOfWeek().ordinal();
        int currentDayAsNum = startDate.getDayOfWeek().ordinal();
        int difference = tripDayAsNum - currentDayAsNum >= 0 ?
                tripDayAsNum - currentDayAsNum :
                tripDayAsNum - currentDayAsNum + DAYS_IN_WEEK;
        startDate = startDate.plusDays(difference);
        LocalDate finalDate = startDate.plusMonths(6);
        List<LocalDate> dates = new ArrayList<>();
        while (startDate.isBefore(finalDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(DAYS_IN_WEEK);
        }
        return dates;
    }
}
