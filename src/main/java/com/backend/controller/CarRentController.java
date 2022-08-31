package com.backend.controller;

import com.backend.service.SkyscannerCarRentService;
import com.backend.skyscanner.rental.car.dto.CarRentInfo;
import com.backend.skyscanner.rental.car.dto.RentACarRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/rental/cars/")
@RequiredArgsConstructor
public class CarRentController {

    private final SkyscannerCarRentService skyscannerCarRentService;

    @GetMapping("searchAll/{pickupId}/{pickupDate}/{pickupTime}/{returnDate}/{returnTime}")
    public ResponseEntity<List<CarRentInfo>> getAllAvailableCarsForRent(@PathVariable String pickupId,
                                                                        @PathVariable String pickupDate,
                                                                        @PathVariable String pickupTime,
                                                                        @PathVariable String returnDate,
                                                                        @PathVariable String returnTime) throws InterruptedException {
        return ResponseEntity.ok(skyscannerCarRentService.fetchAllAvailableCarsToRent(pickupId, pickupDate, pickupTime, returnDate, returnTime));
    }

    @GetMapping("searchByPrice/{maxPrice}/{pickupId}/{pickupDate}/{pickupTime}/{returnDate}/{returnTime}")
    public ResponseEntity<List<CarRentInfo>> getAvailableCarsByMaxPrice(@PathVariable int maxPrice,
                                                                        @PathVariable String pickupId,
                                                                        @PathVariable String pickupDate,
                                                                        @PathVariable String pickupTime,
                                                                        @PathVariable String returnDate,
                                                                        @PathVariable String returnTime) throws InterruptedException {
        return ResponseEntity.ok(skyscannerCarRentService.fetchCarsByMaxPrice(maxPrice, pickupId, pickupDate, pickupTime, returnDate, returnTime));
    }
}
