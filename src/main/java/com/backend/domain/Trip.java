package com.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

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
    private Timestamp departureDate;

    @Column(name = "returnDate")
    private Timestamp returnDate;

    @Column(name = "pricePerPerson")
    private Integer pricePerPerson;
}
