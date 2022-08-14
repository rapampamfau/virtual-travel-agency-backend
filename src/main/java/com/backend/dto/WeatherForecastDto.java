package com.backend.dto;

import com.backend.openweather.City;
import com.backend.openweather.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    @JsonProperty("list")
    private ArrayList<List> list;
    @JsonProperty("city")
    private City city;
}
