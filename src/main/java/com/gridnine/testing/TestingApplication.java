package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightBuilderImpl;
import com.gridnine.testing.service.FlightFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.gridnine.testing.service.FlightBuilderImpl.filterFlights;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
        //метод должен выдать в консоль результаты обработки тестового набора перелётов
        System.out.println(FlightBuilderImpl.createFlights());

        // Создаем список правил для фильтрации
        List<FlightFilter.FlightFilterRule> rules = new ArrayList<>();
        rules.add(FlightFilter.StandardRules.departureInPast());
        rules.add(FlightFilter.StandardRules.arrivalBeforeDeparture());
        rules.add(FlightFilter.StandardRules.maxGroundTime(Duration.ofHours(2)));

        // Фильтруем перелёты
        List<Flight> flights = List.of();
        List<Flight> filteredFlights = filterFlights(flights, rules);

        // Выводим результаты
        System.out.println("All flights:");

        System.out.println("\nFiltered flights:");
    }

    private static List<Flight> filterFlights(List<Flight> flights, List<FlightFilter.FlightFilterRule> rules) {
        return flights;
    }
}



