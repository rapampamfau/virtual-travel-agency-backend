package com.backend.controller;

import com.backend.dto.TripDto;
import com.backend.facade.TripFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TripController.class)
class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripFacade tripFacade;

    @Test
    void shouldFetchAllTrips() throws Exception {
        //Given
        LocalDate departureDate1 = LocalDate.of(2022, 12, 22);
        LocalDate departureDate2 = LocalDate.of(2022, 10, 12);
        List<TripDto> trips = List.of(
                new TripDto(1L, "testFromPlace", "testDestinationPlace", departureDate1,
                        "testAirlineName", "testHotelName", "testCarName", 2000.5,
                        "testForecastedAverageTemperature"),
                new TripDto(2L, "testFromPlace2", "testDestinationPlace2", departureDate2,
                        "testAirlineName2", "testHotelName2", "testCarName2", 3000.2,
                        "testForecastedAverageTemperature2")
        );
        when(tripFacade.fetchAllTrips()).thenReturn(trips);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fromPlace", Matchers.is("testFromPlace")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airlineName", Matchers.is("testAirlineName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fromPlace", Matchers.is("testFromPlace2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].airlineName", Matchers.is("testAirlineName2")));
    }

    @Test
    void shouldFetchSpecificTrip() throws Exception {
        //Given
        LocalDate departureDate = LocalDate.of(2022, 12, 22);
        TripDto tripDto = new TripDto(1L, "testFromPlace", "testDestinationPlace", departureDate,
                "testAirlineName", "testHotelName", "testCarName", 1500.2,
                "testForecastedAverageTemperature");
        when(tripFacade.fetchTrip(1L)).thenReturn(tripDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.summaryPrice", Matchers.is(1500.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.forecastedAverageTemperature", Matchers.is("testForecastedAverageTemperature")));
    }
}
