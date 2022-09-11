package com.backend.controller;

import com.backend.service.SkyscannerHotelsService;
import com.backend.skyscanner.hotels.dto.Highlight;
import com.backend.skyscanner.hotels.dto.HotelDto;
import com.backend.skyscanner.hotels.dto.HotelsLocationRoot;
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
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(HotelsController.class)
class HotelsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkyscannerHotelsService skyscannerHotelsService;

    @Test
    void shouldGetHotelsLocation() throws Exception {
        //Given
        Highlight testHighlight = new Highlight("testEntityName", "testHierarchy");
        List<HotelsLocationRoot> testData = Arrays.asList(
                new HotelsLocationRoot("city|country", "testLocation", "testEntityName", testHighlight, "testId", "testClass"),
                new HotelsLocationRoot("district|city|country", "testLocation2", "testEntityName2", testHighlight, "testId2", "testClass2")
        );
        when(skyscannerHotelsService.fetchHotelsLocation("testCity")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/hotels/locationData/testCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void shouldGetHotelRoomsForGivenLocation() throws Exception {
        //Given
        ArrayList<Double> testCoordinates = new ArrayList<>();
        testCoordinates.add(22.3);
        testCoordinates.add(100.0);
        List<HotelDto> testData = Arrays.asList(
                new HotelDto("1", "testName1", 4, "testDistance", testCoordinates,
                        "testPrice", "1", 50, "testCheapestOffer",
                        "testOfferTypes", "testPricesForm", "testPriceDescription",
                        "testTaxPolicy", "testCheapestOfferPartnerName")
        );
        when(skyscannerHotelsService.fetchHotelRooms(
                "123", 1, 1, "testCheckin", "testCheckout")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/hotels/123/1/1/testCheckin/testCheckout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldGetHotelRoomsForGivenLocationByMaxPrice() throws Exception {
        //Given
        ArrayList<Double> testCoordinates = new ArrayList<>();
        testCoordinates.add(50.0);
        testCoordinates.add(100.0);
        List<HotelDto> testData = Arrays.asList(
                new HotelDto("1", "testName1", 4, "testDistance", testCoordinates,
                        "testPrice", "1", 50, "testCheapestOffer",
                        "testOfferTypes", "testPricesForm", "testPriceDescription",
                        "testTaxPolicy", "testCheapestOfferPartnerName")
        );
        when(skyscannerHotelsService.fetchHotelRoomsByMaxPrice(
                60, "123", 1, 1, "testCheckin", "testCheckout")).thenReturn(testData);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/hotels/searchByMaxPrice/60/123/1/1/testCheckin/testCheckout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}
