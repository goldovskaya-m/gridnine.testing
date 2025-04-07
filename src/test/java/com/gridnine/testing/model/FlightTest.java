package com.gridnine.testing.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FlightTest {
    @Test
    @DisplayName("getFlight() should return the correct list of segments")
    void getFlight_shouldReturnCorrectListOfSegments() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        List<Segment> segments = Arrays.asList(segment1, segment2);
        Flight flight = new Flight(segments);

        assertThat(flight.getSegments()).isEqualTo(segments);
    }

    @Test
    @DisplayName("toString() should return a string representation of the flight")
    void toString_shouldReturnStringRepresentationOfTheFlight() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment1 = new Segment(now, now.plusHours(2));
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(5));
        List<Segment> segments = Arrays.asList(segment1, segment2);
        Flight flight = new Flight(segments);

        String expectedString = segment1.toString() + " " + segment2.toString();
        assertThat(flight.toString()).isEqualTo(expectedString);
    }
}