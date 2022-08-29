package com.backend.skyscanner.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Leg {
    public String id;
    public Origin origin;
    public Destination destination;
    public int durationInMinutes;
    public int stopCount;
    public boolean isSmallestStops;
    public Date departure;
    public Date arrival;
    public int timeDeltaInDays;
    public Carriers carriers;
    public ArrayList<Segment> segments;
}