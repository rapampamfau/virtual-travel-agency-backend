package com.backend.controller;

import com.backend.client.OpenWeatherClient;
import com.backend.dto.WeatherForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final OpenWeatherClient openWeatherClient;

    @GetMapping("forecast/{location}")
    public ResponseEntity<WeatherForecastDto> getWeatherForecast(@PathVariable String location) {
        return ResponseEntity.ok(openWeatherClient.getWeatherForecast(location));
    }
}
