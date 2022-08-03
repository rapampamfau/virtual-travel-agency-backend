package com.backend.controller;

import com.backend.dto.TripDto;
import com.backend.exception.TripNotFoundException;
import com.backend.facade.TripFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripFacade tripFacade;

    @GetMapping
    public ResponseEntity<List<TripDto>> getTrips() {
        return ResponseEntity.ok(tripFacade.fetchAllTrips());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id) throws TripNotFoundException {
        return ResponseEntity.ok(tripFacade.fetchTrip(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTrip(@RequestBody TripDto tripDto) {
        tripFacade.createTrip(tripDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TripDto> updateTrip(@RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripFacade.updateTrip(tripDto));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripFacade.deleteTrip(id);
        return ResponseEntity.ok().build();
    }
}
