package com.backend.facade;

import com.backend.domain.Trip;
import com.backend.dto.TripDto;
import com.backend.exception.TripNotFoundException;
import com.backend.mapper.TripMapper;
import com.backend.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TripFacade {

    private final DbService dbService;
    private final TripMapper tripMapper;

    public List<TripDto> fetchAllTrips() {
        List<Trip> trips = dbService.getAllTrips();
        return tripMapper.mapToTripDtoList(trips);
    }

    public TripDto fetchTrip(final Long tripId) throws TripNotFoundException {
        Trip trip = dbService.getTrip(tripId);
        return tripMapper.mapToTripDto(trip);
    }

    public void createTrip(final TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        dbService.saveTrip(trip);
    }

    public TripDto updateTrip(final TripDto tripDto) {
        Trip trip = tripMapper.mapToTrip(tripDto);
        Trip savedTrip = dbService.saveTrip(trip);
        return tripMapper.mapToTripDto(savedTrip);
    }

    public void deleteTrip(final Long tripId) {
        dbService.deleteTrip(tripId);
    }
}
