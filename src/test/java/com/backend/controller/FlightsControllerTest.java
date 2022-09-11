package com.backend.controller;

import com.backend.dto.AirportDto;
import com.backend.service.SkyscannerFlightsService;
import com.backend.skyscanner.flights.dto.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(FlightsController.class)
class FlightsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkyscannerFlightsService skyscannerFlightsService;

    @Test
    void shouldGetAirportData() throws Exception {
        //Given
        List<AirportDto> testData = Arrays.asList(
                new AirportDto("LHR", "London Heathrow", "London", "United Kingdom")
        );
        when(skyscannerFlightsService.fetchAirportData("London")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/flights/airportData/London")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("London Heathrow")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].iata_code", Matchers.is("LHR")));
    }

    @Test
    void shouldGetBestFlights() throws Exception {
        //Given
        ArrayList<Marketing> testMarketing = new ArrayList<>();
        testMarketing.add(new Marketing("testName1"));
        testMarketing.add(new Marketing("testName2"));

        Carriers testCarriers = new Carriers(testMarketing, "testOperationType");
        Price testPrice = new Price(99, "99");
        Destination testDestination = new Destination("1", "testDestinationName", "testDisplayCode",
                "testCity", false, "testFlightPlaceId", "testType");
        ArrayList<Leg> testLeg = new ArrayList<>();
        testLeg.add(new Leg("123", testDestination, 60, 0, new Date(1L), new Date(2L), testCarriers));

        List<Item> testData = Arrays.asList(
                new Item(testPrice, testLeg)
        );
        when(skyscannerFlightsService.fetchBestFlights(1, "XXX", "YYY", "testDate")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/flights/searchBestFlights/1/XXX/YYY/testDate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetCheapestFlights() throws Exception {
        //Given
        ArrayList<Marketing> testMarketing = new ArrayList<>();
        testMarketing.add(new Marketing("testName1"));
        testMarketing.add(new Marketing("testName2"));

        Carriers testCarriers = new Carriers(testMarketing, "testOperationType");
        Price testPrice = new Price(50, "50");
        Destination testDestination = new Destination("1", "testDestinationName", "testDisplayCode",
                "testCity", false, "testFlightPlaceId", "testType");
        ArrayList<Leg> testLeg = new ArrayList<>();
        testLeg.add(new Leg("123", testDestination, 60, 0, new Date(1L), new Date(2L), testCarriers));

        List<Item> testData = Arrays.asList(
                new Item(testPrice, testLeg)
        );
        when(skyscannerFlightsService.fetchCheapestFlights(1, "XXX", "YYY", "testDate")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/flights/searchCheapestFlights/1/XXX/YYY/testDate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetFastestFlights() throws Exception {
        //Given
        ArrayList<Marketing> testMarketing = new ArrayList<>();
        testMarketing.add(new Marketing("testName1"));
        testMarketing.add(new Marketing("testName2"));

        Carriers testCarriers = new Carriers(testMarketing, "testOperationType");
        Price testPrice = new Price(150, "150");
        Destination testDestination = new Destination("1", "testDestinationName", "testDisplayCode",
                "testCity", false, "testFlightPlaceId", "testType");
        ArrayList<Leg> testLeg = new ArrayList<>();
        testLeg.add(new Leg("123", testDestination, 60, 0, new Date(1L), new Date(2L), testCarriers));

        List<Item> testData = Arrays.asList(
                new Item(testPrice, testLeg)
        );
        when(skyscannerFlightsService.fetchFastestFlights(1, "XXX", "YYY", "testDate")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/flights/searchFastestFlights/1/XXX/YYY/testDate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetDirectFlights() throws Exception {
        //Given
        ArrayList<Marketing> testMarketing = new ArrayList<>();
        testMarketing.add(new Marketing("testName1"));
        testMarketing.add(new Marketing("testName2"));

        Carriers testCarriers = new Carriers(testMarketing, "testOperationType");
        Price testPrice = new Price(199, "199");
        Destination testDestination = new Destination("1", "testDestinationName", "testDisplayCode",
                "testCity", false, "testFlightPlaceId", "testType");
        ArrayList<Leg> testLeg = new ArrayList<>();
        testLeg.add(new Leg("123", testDestination, 60, 0, new Date(1L), new Date(2L), testCarriers));

        List<Item> testData = Arrays.asList(
                new Item(testPrice, testLeg)
        );
        when(skyscannerFlightsService.fetchDirectFlights(1, "XXX", "YYY", "testDate")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/flights/searchDirectFlights/1/XXX/YYY/testDate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
