package com.backend.skyscanner.hotels.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelDto {
    public String hotelId;
    public String name;
    public int stars;
    public String distance;
    public ArrayList<Double> coordinates;
    public String price;
    public String cheapestOfferPartnerId;
    public int rawPrice;
    public String cheapestOffer;
    public String offerTypes;
    public String pricesFrom;
    public String priceDescription;
    public String taxPolicy;
    public String cheapestOfferPartnerName;
}
