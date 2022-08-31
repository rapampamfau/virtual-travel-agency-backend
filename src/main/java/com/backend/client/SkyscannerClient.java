package com.backend.client;

import com.backend.config.SkyscannerConfig;
import com.backend.dto.AirportDto;
import com.backend.skyscanner.flights.dto.FlightsRoot;
import com.backend.skyscanner.hotels.dto.HotelDto;
import com.backend.skyscanner.hotels.dto.HotelsLocationRoot;
import com.backend.skyscanner.hotels.dto.HotelRoomsRoot;
import com.backend.skyscanner.rental.car.dto.RentACarRoot;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SkyscannerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkyscannerClient.class);
    private final SkyscannerConfig skyscannerConfig;
    private final RestTemplate restTemplate;

    public List<AirportDto> getAirportData(String city) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/autocomplete")
                .queryParam("query", city)
                .build()
                .encode()
                .toUri();

        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(setHeaders());
            ResponseEntity<AirportDto[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, AirportDto[].class);
            return Optional.ofNullable(response.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getCity()) && Objects.nonNull(p.getIata_code()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(String.valueOf(e));
            return Collections.emptyList();
        }
    }

    public FlightsRoot searchFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/search")
                .queryParam("adults", adultsNum)
                .queryParam("origin", departure_iata_code)
                .queryParam("destination", destination_iata_code)
                .queryParam("departureDate", departureDate)
                .build()
                .encode()
                .toUri();

        HttpEntity<Void> requestEntity = new HttpEntity<>(setHeaders());
        ResponseEntity<FlightsRoot> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FlightsRoot.class);

        if (response.getBody().getContext() != null) {
            if (Objects.requireNonNull(response.getBody()).getContext().getStatus().equals("incomplete")) {
                LOGGER.info("Incomplete flights results");
            } else {
                LOGGER.info("Complete flights results");
            }
        } else {
            LOGGER.error("Context of flights is null");
        }
        return response.getBody();
    }

    public List<HotelsLocationRoot> getLocationDataForHotelSearch(String city) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/autocomplete-hotel")
                .queryParam("query", city)
                .build()
                .encode()
                .toUri();

        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(setHeaders());
            ResponseEntity<HotelsLocationRoot[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, HotelsLocationRoot[].class);
            return Optional.ofNullable(response.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getLocation()) && Objects.nonNull(p.getEntity_id()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            System.out.println("ERROR " + e);
            return Collections.emptyList();
        }
    }

    public List<HotelDto> searchHotelRooms(String locationId, int adultsNum, int roomsNum, String checkin, String checkout) throws InterruptedException {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/search-hotel")
                .queryParam("locationId", locationId)
                .queryParam("adults", adultsNum)
                .queryParam("rooms", roomsNum)
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .build()
                .encode()
                .toUri();

        HttpEntity<Void> requestEntity = new HttpEntity<>(setHeaders());
        ResponseEntity<HotelRoomsRoot> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, HotelRoomsRoot.class);

        if (Objects.requireNonNull(response.getBody()).getContext().getCompletionPercentage() < 100) {
            LOGGER.info("Incomplete hotels results, another try after 15 seconds");
            Thread.sleep(15000);
            searchHotelRooms(locationId, adultsNum, roomsNum, checkin, checkout);

        } else {
            LOGGER.info("Complete hotels results");
        }
        return response.getBody().getHotels();
    }
    
    public RentACarRoot searchRentACar(String pickupId, String pickupDate, String pickupTime, String returnDate, String returnTime) throws InterruptedException {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/search-rentacar")
                .queryParam("pickupId", pickupId)
                .queryParam("pickupDate", pickupDate)
                .queryParam("pickupTime", pickupTime)
                .queryParam("returnDate", returnDate)
                .queryParam("returnTime", returnTime)
                .build()
                .encode()
                .toUri();
        HttpEntity<Void> requestEntity = new HttpEntity<>(setHeaders());
        ResponseEntity<RentACarRoot> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RentACarRoot.class);

        if (response.getBody().getContext() != null) {
            if (Objects.requireNonNull(response.getBody()).getContext().getStatus().equals("incomplete")) {
                LOGGER.info("Incomplete car rental results, another try after 30 seconds");
                Thread.sleep(30000);
                searchRentACar(pickupId, pickupDate, pickupTime, returnDate, returnTime);
            } else {
                LOGGER.info("Complete car rental results");
            }
        } else {
            LOGGER.error("Context of car rental is null");
        }
        return response.getBody();
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Key", skyscannerConfig.getSkyscannerApiKey());
        headers.set("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com");
        return headers;
    }
}
