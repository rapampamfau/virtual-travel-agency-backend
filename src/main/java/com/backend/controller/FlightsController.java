package com.backend.controller;

import com.backend.dto.AirportDto;
import com.backend.service.SkyscannerFlightsService;
import com.backend.skyscanner.flights.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/flights")
@RequiredArgsConstructor
public class FlightsController {

    private final SkyscannerFlightsService skyscannerFlightsService;

    @GetMapping("airportData/{location}")
    public ResponseEntity<List<AirportDto>> getAirportData(@PathVariable String location) {
        return ResponseEntity.ok(skyscannerFlightsService.fetchAirportData(location));
    }

    @GetMapping("searchBestFlights/{adultsNumber}/{departure}/{destination}/{departureDate}")
    public ResponseEntity<List<Item>> getBestFlights(@PathVariable int adultsNumber,
                                                     @PathVariable String departure,
                                                     @PathVariable String destination,
                                                     @PathVariable String departureDate) {

        return ResponseEntity.ok(skyscannerFlightsService.fetchBestFlights(adultsNumber, departure, destination, departureDate));
    }

    @GetMapping("searchCheapestFlights/{adultsNumber}/{departure}/{destination}/{departureDate}")
    public ResponseEntity<List<Item>> getCheapestFlights(@PathVariable int adultsNumber,
                                                     @PathVariable String departure,
                                                     @PathVariable String destination,
                                                     @PathVariable String departureDate) {

        return ResponseEntity.ok(skyscannerFlightsService.fetchCheapestFlights(adultsNumber, departure, destination, departureDate));
    }

    @GetMapping("searchFastestFlights/{adultsNumber}/{departure}/{destination}/{departureDate}")
    public ResponseEntity<List<Item>> getFastestFlights(@PathVariable int adultsNumber,
                                                     @PathVariable String departure,
                                                     @PathVariable String destination,
                                                     @PathVariable String departureDate) {

        return ResponseEntity.ok(skyscannerFlightsService.fetchFastestFlights(adultsNumber, departure, destination, departureDate));
    }

    @GetMapping("searchDirectFlights/{adultsNumber}/{departure}/{destination}/{departureDate}")
    public ResponseEntity<List<Item>> getDirectFlights(@PathVariable int adultsNumber,
                                                     @PathVariable String departure,
                                                     @PathVariable String destination,
                                                     @PathVariable String departureDate) {

        return ResponseEntity.ok(skyscannerFlightsService.fetchDirectFlights(adultsNumber, departure, destination, departureDate));
    }
}
