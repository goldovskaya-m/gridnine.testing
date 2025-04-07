package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DepartureBeforeNowFilterTest {

    @Test
    @DisplayName("filter() should return flights with departure before now")
    void filter_shouldReturnFlightsWithDepartureBeforeNow() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(new Segment(now.minusDays(1), now)));
        Flight flight2 = new Flight(Arrays.asList(new Segment(now.plusDays(1), now.plusDays(2))));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).containsExactly(flight1);
    }

    @Test
    @DisplayName("filter() should return an empty list if there are no flights with departure before now")
    void filter_shouldReturnEmptyListWhenNoFlightsDepartBeforeNow() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(new Segment(now.plusDays(1), now.plusDays(2))));
        Flight flight2 = new Flight(Arrays.asList(new Segment(now.plusDays(3), now.plusDays(4))));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).isEmpty();
    }

    @Test
    @DisplayName("filter() should handle multiple segments correctly")
    void filter_shouldHandleMultipleSegmentsCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now.minusDays(1), now);
        Segment segment2 = new Segment(now.plusHours(1), now.plusHours(2));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(new Segment(now.plusDays(1), now.plusDays(2))));

        List<Flight> flights = Arrays.asList(flight1, flight2);
        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).containsExactly(flight1);
    }
}
