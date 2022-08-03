package com.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class TripDto {

    private final Long id;
    private final String fromPlace;
    private final String destinationPlace;
    private final Date departureDate;
    private final Date returnDate;
    private final Integer pricePerPerson;
}
