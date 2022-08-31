package com.backend.skyscanner.flights.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {
    public String id;
    public Destination destination;
    public int durationInMinutes;
    public int stopCount;
    public Date departure;
    public Date arrival;
    public Carriers carriers;
}
