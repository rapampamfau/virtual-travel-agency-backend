package com.backend.mapper;

import com.backend.domain.Trip;
import com.backend.dto.TripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripMapper {

    public Trip mapToTrip(final TripDto tripDto) {
        return new Trip(
                tripDto.getId(),
                tripDto.getFromPlace(),
                tripDto.getDestinationPlace(),
                tripDto.getDepartureDate(),
                tripDto.getReturnDate(),
                tripDto.getPricePerPerson()
        );
    }

    public TripDto mapToTripDto(final Trip trip) {
        return new TripDto(
                trip.getId(),
                trip.getFromPlace(),
                trip.getDestinationPlace(),
                trip.getDepartureDate(),
                trip.getReturnDate(),
                trip.getPricePerPerson()
        );
    }

    public List<TripDto> mapToTripDtoList(final List<Trip> trips) {
        return trips.stream()
                .map(this::mapToTripDto)
                .collect(Collectors.toList());
    }
}
