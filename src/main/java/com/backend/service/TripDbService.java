package com.backend.service;

import com.backend.domain.Trip;
import com.backend.exception.TripNotFoundException;
import com.backend.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripDbService {

    private final TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTrip(final Long tripId) throws TripNotFoundException {
        return tripRepository.findById(tripId).orElseThrow(TripNotFoundException::new);
    }

    public Trip saveTrip(final Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(final Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
