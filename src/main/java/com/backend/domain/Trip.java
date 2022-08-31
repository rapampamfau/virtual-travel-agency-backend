package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "trips")
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fromPlace")
    private String fromPlace;

    @Column(name = "destinationPlace")
    private String destinationPlace;

    @Column(name = "departureDate")
    private LocalDate departureDate;

    @Column(name = "airlineName")
    private String airlineName;

    @Column(name = "hotelName")
    private String hotelName;

    @Column(name = "carName")
    private String carName;

    @Column(name = "summaryPrice")
    private Double summaryPrice;

    @Column(name = "forecastedAverageTemperature")
    private String forecastedAverageTemperature;
}
