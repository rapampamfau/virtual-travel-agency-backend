package com.backend.service;

import com.backend.client.SkyscannerClient;
import com.backend.skyscanner.rental.car.dto.CarRentInfo;
import com.backend.skyscanner.rental.car.dto.RentACarRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkyscannerCarRentService {

    private final SkyscannerClient skyscannerClient;

    public List<CarRentInfo> fetchAllAvailableCarsToRent(String pickupId, String pickupDate, String pickupTime, String returnDate, String returnTime) throws InterruptedException {
        RentACarRoot fetchedData = skyscannerClient.searchRentACar(pickupId, pickupDate, pickupTime, returnDate, returnTime);
        return fetchedData.getQuotes();
    }

    public List<CarRentInfo> fetchCarsByMaxPrice(int maxPrice, String pickupId, String pickupDate, String pickupTime, String returnDate, String returnTime) throws InterruptedException {
        RentACarRoot fetchedData = skyscannerClient.searchRentACar(pickupId, pickupDate, pickupTime, returnDate, returnTime);
        return fetchedData.getQuotes().stream()
                .filter(carRentInfo -> carRentInfo.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
