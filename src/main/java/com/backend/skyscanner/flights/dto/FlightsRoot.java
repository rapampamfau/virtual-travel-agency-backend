package com.backend.skyscanner.flights.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightsRoot {
    private Itineraries itineraries;
    private Context context;
}
