package com.backend.controller;

import com.backend.service.SkyscannerHotelsService;
import com.backend.skyscanner.hotels.dto.HotelDto;
import com.backend.skyscanner.hotels.dto.HotelsLocationRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/hotels/")
@RequiredArgsConstructor
public class HotelsController {

    private final SkyscannerHotelsService skyscannerHotelsService;

    @GetMapping("locationData/{city}")
    public ResponseEntity<List<HotelsLocationRoot>> getHotelsLocation(@PathVariable String city) {
        return ResponseEntity.ok(skyscannerHotelsService.fetchHotelsLocation(city));
    }

    @GetMapping("{location_id}/{adultsNum}/{roomsNum}/{checkin}/{checkout}")
    public ResponseEntity<List<HotelDto>> getHotelRoomsForGivenLocation(@PathVariable String location_id,
                                                                        @PathVariable int adultsNum,
                                                                        @PathVariable int roomsNum,
                                                                        @PathVariable String checkin,
                                                                        @PathVariable String checkout) throws InterruptedException {
        return ResponseEntity.ok(skyscannerHotelsService.fetchHotelRooms(location_id, adultsNum, roomsNum, checkin, checkout));
    }

    @GetMapping("searchByMaxPrice/{maxPrice}/{location_id}/{adultsNum}/{roomsNum}/{checkin}/{checkout}")
    public ResponseEntity<List<HotelDto>> getHotelRoomsForGivenLocation(@PathVariable int maxPrice,
                                                                        @PathVariable String location_id,
                                                                        @PathVariable int adultsNum,
                                                                        @PathVariable int roomsNum,
                                                                        @PathVariable String checkin,
                                                                        @PathVariable String checkout) throws InterruptedException {
        return ResponseEntity.ok(skyscannerHotelsService.fetchHotelRoomsByMaxPrice(maxPrice, location_id, adultsNum, roomsNum, checkin, checkout));
    }
}
