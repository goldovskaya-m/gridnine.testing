package com.gridnine.testing.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SegmentTest {
    @Test
    @DisplayName("Segment constructor should throw NullPointerException when departure date is null")
    void segmentConstructor_shouldThrowNullPointerExceptionWhenDepartureDateIsNull() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        assertThrows(NullPointerException.class, () -> new Segment(null, arrivalDate));
    }

    @Test
    @DisplayName("Segment constructor should throw NullPointerException when arrival date is null")
    void segmentConstructor_shouldThrowNullPointerExceptionWhenArrivalDateIsNull() {
        LocalDateTime departureDate = LocalDateTime.now();
        assertThrows(NullPointerException.class, () -> new Segment(departureDate, null));
    }

    @Test
    @DisplayName("getDepartureDate() should return the correct departure date")
    void getDepartureDate_shouldReturnCorrectDepartureDate() {
        LocalDateTime departureDate = LocalDateTime.now();
        LocalDateTime arrivalDate = LocalDateTime.now().plusHours(2);
        Segment segment = new Segment(departureDate, arrivalDate);
        assertThat(segment.getDepartureDate()).isEqualTo(departureDate);
    }

    @Test
    @DisplayName("getArrivalDate() should return the correct arrival date")
    void getArrivalDate_shouldReturnCorrectArrivalDate() {
        LocalDateTime departureDate = LocalDateTime.now();
        LocalDateTime arrivalDate = LocalDateTime.now().plusHours(2);
        Segment segment = new Segment(departureDate, arrivalDate);
        assertThat(segment.getArrivalDate()).isEqualTo(arrivalDate);
    }

    @Test
    @DisplayName("toString() should return a formatted string representation of the segment")
    void toString_shouldReturnFormattedStringRepresentation() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 1, 1, 12, 0);
        Segment segment = new Segment(departureDate, arrivalDate);
        assertThat(segment.toString()).isEqualTo("[2024-01-01T10:00|2024-01-01T12:00]");
    }
}