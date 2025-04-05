package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class FlightFilter {

    public static BinaryOperator<FlightFilter.FlightFilterRule> FlightFilterRule;

    public FlightFilter(List<Segment> segments) {

    }

    public static List<Flight> filterFlights(List<Flight> flights, FlightFilter.FlightFilterRule flightFilterRule) {

        return flights;
    }

    /**
     * Интерфейс для правил фильтрации перелётов
     */
    @FunctionalInterface
    public interface FlightFilterRule extends Predicate<Flight> {
        // Наследует метод test(Flight flight) от Predicate<Flight>
        }

    /**
     * Стандартные правила фильтрации
     */
    public static class StandardRules {
        // Перелёт не должен содержать сегментов с вылетом в прошлом
        public static FlightFilterRule departureInPast() {
            return flight -> flight.getSegments().stream()
                    .noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
        }

        // Перелёт не должен содержать сегментов с прибытием раньше вылета
        public static FlightFilterRule arrivalBeforeDeparture() {
            return flight -> flight.getSegments().stream()
                    .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
        }

        // Перелёт не должен содержать более 2 часов ожидания между сегментами
        public static FlightFilterRule maxGroundTime(Duration maxGroundTime) {
            return flight -> {
                List<Segment> segments = flight.getSegments();
                if (segments.size() < 2) return true;

                for (int i = 0; i < segments.size() - 1; i++) {
                    LocalDateTime currentArrival = segments.get(i).getArrivalDate();
                    LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
                    Duration groundTime = Duration.between(currentArrival, nextDeparture);

                    if (groundTime.compareTo(maxGroundTime) > 0) {
                        return false;
                    }
                }
                return true;
            };
        }
    }
}
