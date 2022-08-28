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

    private final TripRepository repository;

    public List<Trip> getAllTrips() {
        return repository.findAll();
    }

    public Trip getTrip(final Long tripId) throws TripNotFoundException {
        return repository.findById(tripId).orElseThrow(TripNotFoundException::new);
    }

    public Trip saveTrip(final Trip trip) {
        return repository.save(trip);
    }

    public void deleteTrip(final Long tripId) {
        repository.deleteById(tripId);
    }
}
