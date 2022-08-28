package com.backend.facade;

import com.backend.domain.Trip;
import com.backend.dto.TripDto;
import com.backend.exception.TripNotFoundException;
import com.backend.mapper.TripMapper;
import com.backend.service.TripDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TripFacade {

    private final TripDbService tripDbService;
    private final TripMapper tripMapper;

    public List<TripDto> fetchAllTrips() {
        List<Trip> trips = tripDbService.getAllTrips();
        return tripMapper.mapToTripDtoList(trips);
    }

    public TripDto fetchTrip(final Long tripId) throws TripNotFoundException {
        Trip trip = tripDbService.getTrip(tripId);
        return tripMapper.mapToTripDto(trip);
    }

    public void createTrip(final TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        tripDbService.saveTrip(trip);
    }

    public TripDto updateTrip(final TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        Trip savedTrip = tripDbService.saveTrip(trip);
        return tripMapper.mapToTripDto(savedTrip);
    }

    public void deleteTrip(final Long tripId) {
        tripDbService.deleteTrip(tripId);
    }
}
