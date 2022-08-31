package com.backend.controller;

import com.backend.service.SkyscannerCarRentService;
import com.backend.skyscanner.rental.car.dto.CarRentInfo;
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

import java.util.*;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CarRentController.class)
class CarRentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkyscannerCarRentService skyscannerCarRentService;

    @Test
    void shouldFetchAllCars() throws Exception {
        //Given
        List<CarRentInfo> testData = Arrays.asList(
                new CarRentInfo("TestCar", 100.0),
                new CarRentInfo("TestCar2", 120.0)
        );
        when(skyscannerCarRentService.fetchAllAvailableCarsToRent(
                "testId",
                "2022-09-10",
                "10:00",
                "2022-09-17",
                "10:00"
                )).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/rental/cars/searchAll/testId/2022-09-10/10:00/2022-09-17/10:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void shouldFetchCarsToMaxGivenPrice() throws Exception {
        //Given
        List<CarRentInfo> testData = Arrays.asList(
                new CarRentInfo("TestCar", 100.0)
        );
        when(skyscannerCarRentService.fetchCarsByMaxPrice(
                110,
                "testId",
                "2022-09-10",
                "10:00",
                "2022-09-17",
                "10:00"
        )).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rental/cars/searchByPrice/110/testId/2022-09-10/10:00/2022-09-17/10:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}