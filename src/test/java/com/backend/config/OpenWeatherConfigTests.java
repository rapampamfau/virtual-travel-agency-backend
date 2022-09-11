package com.backend.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class OpenWeatherConfigTests {

    @Test
    void shouldNotShowInformation() {
        //Given
        OpenWeatherConfig config = new OpenWeatherConfig();
        //When
        String openWeatherForecastApiEndpoint = config.getOpenWeatherForecastApiEndpoint();
        String openWeatherAppKey = config.getOpenWeatherAppKey();
        //Then
        assertNull(openWeatherForecastApiEndpoint);
        assertNull(openWeatherAppKey);
    }
}
