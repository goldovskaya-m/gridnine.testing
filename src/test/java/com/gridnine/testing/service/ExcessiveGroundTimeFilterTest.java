package com.gridnine.testing.service;

import org.junit.jupiter.api.Test;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExcessiveGroundTimeFilterTest {

    @Test
    @DisplayName("filter() should return flights with excessive ground time")
    void filter_shouldReturnFlightsWithExcessiveGroundTime() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(5), now.plusHours(6));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(now, now.plusHours(1));
        Segment segment4 = new Segment(now.plusHours(2), now.plusHours(3));
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        List<Flight> flights = Arrays.asList(flight1, flight2);
        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter(Duration.ofHours(2));
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).containsExactly(flight1);
    }

    @Test
    @DisplayName("filter() should return an empty list if there are no flights with excessive ground time")
    void filter_shouldReturnEmptyListWhenNoFlightsHaveExcessiveGroundTime() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(1));
        Segment segment2 = new Segment(now.plusHours(2), now.plusHours(3));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(now, now.plusHours(1));
        Segment segment4 = new Segment(now.plusHours(1).plusMinutes(30), now.plusHours(3));
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        List<Flight> flights = Arrays.asList(flight1, flight2);
        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter(Duration.ofHours(2));
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).isEmpty();
    }

    @Test
    @DisplayName("filter() should handle flights with single segment")
    void filter_shouldHandleFlightsWithSingleSegment() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(1));
        Flight flight1 = new Flight(Collections.singletonList(segment1));

        List<Flight> flights = Collections.singletonList(flight1);
        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter(Duration.ofHours(2));
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).isEmpty();
    }

    @Test
    @DisplayName("filter() should handle zero ground time correctly")
    void filter_shouldHandleZeroGroundTimeCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(1));
        Segment segment2 = new Segment(now.plusHours(1), now.plusHours(2));
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        List<Flight> flights = Collections.singletonList(flight1);
        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter(Duration.ofHours(2));
        List<Flight> filteredFlights = filter.filter(flights);

        assertThat(filteredFlights).isEmpty();
    }
}
