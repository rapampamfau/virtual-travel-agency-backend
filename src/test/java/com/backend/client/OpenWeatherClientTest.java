package com.backend.client;

import com.backend.config.OpenWeatherConfig;
import com.backend.dto.WeatherForecastDto;
import com.backend.openweather.City;
import com.backend.openweather.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenWeatherClientTest {

    @InjectMocks
    private OpenWeatherClient openWeatherClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OpenWeatherConfig openWeatherConfig;

    @Test
    void shouldGetWeatherForecast() throws URISyntaxException {
        // Given
        when(openWeatherConfig.getOpenWeatherForecastApiEndpoint()).thenReturn("http://test.com/data/2.5/forecast");
        when(openWeatherConfig.getOpenWeatherAppKey()).thenReturn("test");

        City city = new City();
        ArrayList<List> list = new ArrayList<>();

        WeatherForecastDto forecastDto = new WeatherForecastDto(list, city);

        URI uri = new URI("http://test.com/data/2.5/forecast?q=city&appid=test&units=metric");
        when(restTemplate.getForObject(uri, WeatherForecastDto.class)).thenReturn(forecastDto);

        //When
        WeatherForecastDto fetchedWeatherForecast = openWeatherClient.getWeatherForecast("city");

        //Then
        assertEquals(list, fetchedWeatherForecast.getList());
        assertEquals(city, fetchedWeatherForecast.getCity());
    }
}