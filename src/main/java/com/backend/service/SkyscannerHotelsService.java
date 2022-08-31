package com.backend.service;

import com.backend.client.SkyscannerClient;
import com.backend.skyscanner.hotels.dto.HotelDto;
import com.backend.skyscanner.hotels.dto.HotelsLocationRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkyscannerHotelsService {

    private final SkyscannerClient skyscannerClient;

    public List<HotelsLocationRoot> fetchHotelsLocation(String city) {
        return skyscannerClient.getLocationDataForHotelSearch(city);
    }

    public List<HotelDto> fetchHotelRooms(String locationId, int adultsNum, int roomsNum, String checkin, String checkout) throws InterruptedException {
        return skyscannerClient.searchHotelRooms(locationId, adultsNum, roomsNum, checkin, checkout);
    }

    public List<HotelDto> fetchHotelRoomsByMaxPrice(int maxPrice, String locationId, int adultsNum, int roomsNum, String checkin, String checkout) throws InterruptedException {
        List<HotelDto> fetchedData = skyscannerClient.searchHotelRooms(locationId, adultsNum, roomsNum, checkin, checkout);
        return fetchedData.stream()
                .filter(hotel -> Integer.parseInt(hotel.getPrice().split(" ")[0]) <= maxPrice)
                .collect(Collectors.toList());
    }
}
