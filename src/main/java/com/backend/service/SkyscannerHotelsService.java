package com.backend.service;

import com.backend.client.SkyscannerClient;
import com.backend.skyscanner.hotels.dto.HotelRoomsRoot;
import com.backend.skyscanner.hotels.dto.HotelsLocationRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkyscannerHotelsService {

    private final SkyscannerClient skyscannerClient;

    public List<HotelsLocationRoot> fetchHotelsLocation(String city) {
        return skyscannerClient.getLocationDataForHotelSearch(city);
    }

    public HotelRoomsRoot fetchHotelRooms(String locationId, int adultsNum, int roomsNum, String checkin, String checkout) throws InterruptedException {
        return skyscannerClient.searchHotelRooms(locationId, adultsNum, roomsNum, checkin, checkout);
    }
}
