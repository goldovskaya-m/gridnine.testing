package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FlightBuilderTest {
    @Test
    @DisplayName("createFlights() should return a list of flights")
    void createFlights_shouldReturnListOfFlights() {
        List<Flight> flights = FlightBuilder.createFlights();
        assertThat(flights).isNotEmpty();
    }

    @Test
    @DisplayName("createFlight() should throw IllegalArgumentException when given odd number of dates")
    void createFlight_shouldThrowIllegalArgumentExceptionForOddNumberOfDates() {
        LocalDateTime now = LocalDateTime.now();
        assertThrows(IllegalArgumentException.class, () -> FlightBuilder.createFlight(now));
    }

    @Test
    @DisplayName("createFlight() should create a flight with segments when given even number of dates")
    void createFlight_shouldCreateFlightWithSegmentsForEvenNumberOfDates() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight = FlightBuilder.createFlight(now, now.plusHours(2));
        assertThat(flight.getSegments()).hasSize(1);
        assertThat(flight.getSegments().get(0).getDepartureDate()).isEqualTo(now);
        assertThat(flight.getSegments().get(0).getArrivalDate()).isEqualTo(now.plusHours(2));
    }

    @Test
    void createFlights() {
    }
}