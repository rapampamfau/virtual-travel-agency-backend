package com.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenWeatherConfig {

    @Value("${open.weather.forecast.api.endpoint}")
    private String openWeatherForecastApiEndpoint;

    @Value("${open.weather.app.key}")
    private String openWeatherAppKey;
}
