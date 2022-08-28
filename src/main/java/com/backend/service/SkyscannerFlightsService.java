package com.backend.service;

import com.backend.client.SkyscannerClient;
import com.backend.dto.AirportDto;
import com.backend.skyscanner.flights.dto.Root;
import com.backend.skyscanner.flights.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkyscannerFlightsService {

    private final SkyscannerClient skyscannerClient;

    public List<AirportDto> fetchAirportData(String location) {
        return skyscannerClient.getAirportData(location);
    }

    public List<Item> fetchData(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate, String filterType) {
        Root fetchedData = skyscannerClient.searchFlights(
                adultsNum,
                departure_iata_code,
                destination_iata_code,
                departureDate);

        return fetchedData.getItineraries().getBuckets().stream()
                .filter(bucket -> bucket.getId().equals(filterType))
                .flatMap(bucket -> bucket.getItems().stream())
                .collect(Collectors.toList());
    }

    public List<Item> fetchBestFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
        return fetchData(adultsNum, departure_iata_code, destination_iata_code, departureDate, "Best");
    }

    public List<Item> fetchCheapestFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
        return fetchData(adultsNum, departure_iata_code, destination_iata_code, departureDate, "Cheapest");
    }

    public List<Item> fetchDirectFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
        return fetchData(adultsNum, departure_iata_code, destination_iata_code, departureDate, "Direct");
    }

    public List<Item> fetchFastestFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
        return fetchData(adultsNum, departure_iata_code, destination_iata_code, departureDate, "Fastest");
    }
}
