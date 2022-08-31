package com.backend.skyscanner.flights.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination {
    public String id;
    public String name;
    public String displayCode;
    public String city;
    public boolean isHighlighted;
    public String flightPlaceId;
    public String type;
}
