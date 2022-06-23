package com.tsh.frantishex.tripServiceTest.service;


import com.tsh.frantishex.TshApplication;
import com.tsh.frantishex.tripService.service.TripServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Sql("/tripProperties/data-trip.sql")
@TestPropertySource(locations= "classpath:tripProperties/applicationTripTest.properties")
public class TripServiceTest {
    private final Logger logger = Logger.getLogger(TshApplication.class.getName());

    @Autowired
    private TripServiceImpl tripService;

    @Test
    void citiesFromTest() {
        Set<String> filteredTrips = tripService.citiesFrom();
        Set<String> expectedTrips = new HashSet<>(Arrays.asList("Sofia", "Belovo"));
        filteredTrips.forEach(logger::info);
        assertThat(filteredTrips).isEqualTo(expectedTrips);

    }

    @Test
    void citiesToTest() {
        Set<String> filteredTrips = tripService.citiesTo("Sofia");
        Set<String> expectedTrips = new HashSet<>(Arrays.asList("Ihtiman", "Kostenetz"));
        filteredTrips.forEach(logger::info);
        assertThat(filteredTrips).isEqualTo(expectedTrips);
    }

    @Test
    void findAllBuyOnlineCitiesTest() {
        Set<String> filteredTrips = tripService.findAllBuyOnlineCities();
        Set<String> expectedTrips = new HashSet<>(Arrays.asList("Belovo"));
        filteredTrips.forEach(logger::info);
        assertThat(filteredTrips).isEqualTo(expectedTrips);

    }

    @Test
    void findTripsByStartAndDestinationCitiesTest() {
        List<String> filteredTrips = tripService.findTripsByStartAndDestinationCitiesAndDate("Sofia", "Ihtiman", "2022-06-22");
        List<String> expectedTrips = Arrays.asList("Sofia-Plovdiv");
        filteredTrips.forEach(logger::info);
        assertThat(filteredTrips).isEqualTo(expectedTrips);
    }

}
