package com.backend.openweather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    public int id;
    public String main;
    public String description;
    public String icon;
}
