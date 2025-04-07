package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.*;

import java.time.Duration;
import java.util.List;


public class FlightBuilderCreateFlightsTest {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        //метод должен выдать в консоль результаты обработки тестового набора перелётов
        System.out.println(FlightBuilder.createFlights());

        // Выводим результаты
        System.out.println("All flights:");

        System.out.println("Исходный список перелётов:");
        flights.forEach(System.out::println);
        System.out.println();

        // Выводим результат
        System.out.println("\nFiltered flights:");

        // Вылет до текущего момента времени
        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlights1 = departureBeforeNowFilter.filter(flights);
        System.out.println("Перелёты с вылетом до текущего момента времени:");
        filteredFlights1.forEach(System.out::println);
        System.out.println();

        // Сегменты с датой прилёта раньше даты вылета
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> filteredFlights2 = arrivalBeforeDepartureFilter.filter(flights);
        System.out.println("Перелёты с датой прилёта раньше даты вылета:");
        filteredFlights2.forEach(System.out::println);
        System.out.println();

        // Перелеты, где общее время, проведённое на земле, превышает два часа
        FlightFilter excessiveGroundTimeFilter = new ExcessiveGroundTimeFilter(Duration.ofHours(2));
        List<Flight> filteredFlights3 = excessiveGroundTimeFilter.filter(flights);
        System.out.println("Перелёты, где общее время на земле превышает два часа:");
        filteredFlights3.forEach(System.out::println);
    }
}



