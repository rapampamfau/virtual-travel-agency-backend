package com.backend.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    public int id;
    public String name;
    public String country;
    public int population;
    public int timezone;
    public int sunrise;
    public int sunset;
}
