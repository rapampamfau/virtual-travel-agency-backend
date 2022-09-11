package com.backend.controller;

import com.backend.dto.WeatherForecastDto;
import com.backend.service.OpenWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenWeatherService openWeatherService;

    @Test
    void shouldGetAverageTemp() throws Exception {
        //Given
        WeatherForecastDto testWeatherForecast = new WeatherForecastDto();
        when(openWeatherService.calculateAverageTemperatureFor5Days(testWeatherForecast)).thenReturn(22.3);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather/forecast/testLocation/temperature")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
