package com.backend.controller;

import com.backend.dto.WeatherForecastDto;
import com.backend.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final OpenWeatherService openWeatherService;

    @GetMapping("forecast/{location}")
    public ResponseEntity<WeatherForecastDto> getWeatherForecast(@PathVariable String location) {
        return ResponseEntity.ok(openWeatherService.fetchWeatherForecast(location));
    }

    @GetMapping("forecast/{location}/temperature")
    public ResponseEntity<Double> getAverageTemp(@PathVariable String location) {
        WeatherForecastDto forecastDto = openWeatherService.fetchWeatherForecast(location);
        return ResponseEntity.ok(openWeatherService.calculateAverageTemperatureFor5Days(forecastDto));
    }
}
