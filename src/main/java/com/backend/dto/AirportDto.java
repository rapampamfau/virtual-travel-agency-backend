package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {
    private String iata_code;
    private String name;
    private String city;
    private String country;
}
