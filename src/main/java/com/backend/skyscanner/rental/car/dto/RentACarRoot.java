package com.backend.skyscanner.rental.car.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentACarRoot {
        @JsonProperty("quotes")
        public List<CarRentInfo> quotes;
        @JsonProperty("quotes_count")
        public int quotes_count;
        @JsonProperty("context")
        public Context context;
}
