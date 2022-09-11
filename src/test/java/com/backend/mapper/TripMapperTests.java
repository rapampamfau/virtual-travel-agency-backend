package com.backend.mapper;

import com.backend.domain.Trip;
import com.backend.dto.TripDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripMapperTests {

    private TripMapper tripMapper = new TripMapper();

    @Test
    void mapToTrip() {
        //Given
        LocalDate departureDate = LocalDate.of(2022, 12, 22);
        TripDto tripDto = new TripDto(1L, "testFromPlace", "testDestinationPlace", departureDate,
                "testAirlineName", "testHotelName", "testCarName", 1500.2,
                "testForecastedAverageTemperature");
        //When
        Trip testTrip = tripMapper.mapToTrip(tripDto);
        //Then
        assertEquals(tripDto.getId(), testTrip.getId());
        assertEquals(tripDto.getFromPlace(), testTrip.getFromPlace());
        assertEquals(tripDto.getDestinationPlace(), testTrip.getDestinationPlace());
        assertEquals(tripDto.getDepartureDate(), testTrip.getDepartureDate());
        assertEquals(tripDto.getAirlineName(), testTrip.getAirlineName());
        assertEquals(tripDto.getHotelName(), testTrip.getHotelName());
        assertEquals(tripDto.getCarName(), testTrip.getCarName());
        assertEquals(tripDto.getSummaryPrice(), testTrip.getSummaryPrice());
        assertEquals(tripDto.getForecastedAverageTemperature(), testTrip.getForecastedAverageTemperature());
    }

    @Test
    void mapTripDto() {
        //Given
        LocalDate departureDate = LocalDate.of(2022, 12, 22);
        Trip trip = new Trip(1L, "testFromPlace", "testDestinationPlace", departureDate,
                "testAirlineName", "testHotelName", "testCarName", 1500.2,
                "testForecastedAverageTemperature");
        //When
        TripDto testTripDto = tripMapper.mapToTripDto(trip);
        //Then
        assertEquals(trip.getId(), testTripDto.getId());
        assertEquals(trip.getFromPlace(), testTripDto.getFromPlace());
        assertEquals(trip.getDestinationPlace(), testTripDto.getDestinationPlace());
        assertEquals(trip.getDepartureDate(), testTripDto.getDepartureDate());
        assertEquals(trip.getAirlineName(), testTripDto.getAirlineName());
        assertEquals(trip.getHotelName(), testTripDto.getHotelName());
        assertEquals(trip.getCarName(), testTripDto.getCarName());
        assertEquals(trip.getSummaryPrice(), testTripDto.getSummaryPrice());
        assertEquals(trip.getForecastedAverageTemperature(), testTripDto.getForecastedAverageTemperature());
    }

    @Test
    void mapToTripDtoList() {
        //Given
        List<Trip> trips = new ArrayList<>();
        LocalDate departureDate = LocalDate.of(2022, 12, 22);
        Trip trip = new Trip(1L, "testFromPlace", "testDestinationPlace", departureDate,
                "testAirlineName", "testHotelName", "testCarName", 1500.2,
                "testForecastedAverageTemperature");
        trips.add(trip);
        //When
        List<TripDto> tripDtos = tripMapper.mapToTripDtoList(trips);
        //Then
        assertEquals(trips.get(0).getId(), tripDtos.get(0).getId());
        assertEquals(trips.get(0).getFromPlace(), tripDtos.get(0).getFromPlace());
        assertEquals(trips.get(0).getDestinationPlace(), tripDtos.get(0).getDestinationPlace());
        assertEquals(trips.get(0).getDepartureDate(), tripDtos.get(0).getDepartureDate());
        assertEquals(trips.get(0).getAirlineName(), tripDtos.get(0).getAirlineName());
        assertEquals(trips.get(0).getHotelName(), tripDtos.get(0).getHotelName());
        assertEquals(trips.get(0).getCarName(), tripDtos.get(0).getCarName());
        assertEquals(trips.get(0).getSummaryPrice(), tripDtos.get(0).getSummaryPrice());
        assertEquals(trips.get(0).getForecastedAverageTemperature(), tripDtos.get(0).getForecastedAverageTemperature());
    }
}
