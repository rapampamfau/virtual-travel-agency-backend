package com.backend.client;

import com.backend.config.OpenWeatherConfig;
import com.backend.dto.WeatherForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class OpenWeatherClient {

    private final OpenWeatherConfig openWeatherConfig;
    private final RestTemplate restTemplate;

    public WeatherForecastDto getWeatherForecast(String location) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(openWeatherConfig.getOpenWeatherForecastApiEndpoint())
                .queryParam("q", location)
                .queryParam("appid", openWeatherConfig.getOpenWeatherAppKey())
                .queryParam("units", "metric")
                .build()
                .encode()
                .toUri();
        try {
            return restTemplate.getForObject(url, WeatherForecastDto.class);
        } catch (RestClientException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
