package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ExcessiveGroundTimeFilter implements FlightFilter {
    private final Duration maxGroundTime;

    public ExcessiveGroundTimeFilter(Duration maxGroundTime) {
        this.maxGroundTime = maxGroundTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() <= 1) {
                        return false;
                    }

                    Duration totalGroundTime = Duration.ZERO;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        LocalDateTime arrival = segments.get(i).getArrivalDate();
                        LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
                        totalGroundTime = totalGroundTime.plus(Duration.between(arrival, nextDeparture));
                    }

                    return totalGroundTime.compareTo(maxGroundTime) > 0;
                })
                .collect(Collectors.toList());
    }
}
