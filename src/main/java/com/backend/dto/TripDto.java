package com.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private String fromPlace;
    private String destinationPlace;
    private LocalDate departureDate;
    private String airlineName;
    private String hotelName;
    private String carName;
    private Double summaryPrice;
    private String forecastedAverageTemperature;
}
