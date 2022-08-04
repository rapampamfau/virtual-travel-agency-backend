package com.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class TripDto {

    private final Long id;
    private final String fromPlace;
    private final String destinationPlace;
    private final Timestamp departureDate;
    private final Timestamp returnDate;
    private final Integer pricePerPerson;
}
