package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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
    private Date departureDate;

    @Column(name = "returnDate")
    private Date returnDate;

    @Column(name = "pricePerPerson")
    private Integer pricePerPerson;
}
