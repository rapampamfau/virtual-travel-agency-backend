package com.backend.service;

import com.backend.client.OpenWeatherClient;
import com.backend.dto.WeatherForecastDto;
import com.backend.openweather.List;
import com.backend.openweather.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

    private final OpenWeatherClient openWeatherClient;

    public WeatherForecastDto fetchWeatherForecast(String location) {
        return openWeatherClient.getWeatherForecast(location);
    }

    public double calculateAverageTemperatureFor5Days(WeatherForecastDto forecastDto) {
        OptionalDouble averageTemp = forecastDto.getList().stream()
                .map(List::getMain)
                .mapToDouble(Main::getTemp)
                .average();

        return averageTemp.getAsDouble();
    }
}
