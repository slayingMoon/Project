package com.example.tsh.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    void findAllStartingCitiesTest() {
        List<String> filteredTrips = tripService.findAllStartingCities();
        filteredTrips.forEach(System.out::println);
    }

    @Test
    void findAllPossibleDestinationsTest() {
        List<String> filteredTrips = tripService.findAllPossibleDestinations("Sofia");
        filteredTrips.forEach(System.out::println);
    }

    @Test
    void findAllBuyOnlineCitiesTest() {
        List<String> filteredTrips = tripService.findAllBuyOnlineCities();
        filteredTrips.forEach(System.out::println);
    }

    @Test
    void findTripsByStartAndDestinationCitiesTest() {
        List<String> filteredTrips = tripService.findTripsByStartAndDestinationCities("Sofia", "Ihtiman");
        filteredTrips.forEach(System.out::println);
    }
    
    @Test
    void generateTripDatesForNextYear() {
        List<String> dates = tripService.generateTripDates(2l);
        System.out.println();
    }
}
