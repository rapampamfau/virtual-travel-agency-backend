package com.backend.client;

import com.backend.config.SkyscannerConfig;
import com.backend.dto.AirportDto;
import com.backend.skyscanner.flights.dto.Root;
import lombok.RequiredArgsConstructor;
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

    private final SkyscannerConfig skyscannerConfig;
    private final RestTemplate restTemplate;

    public List<AirportDto> getAirportData(String location) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "/autocomplete")
                .queryParam("query", location)
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
            System.out.println("ERROR " + e);
            return Collections.emptyList();
        }
    }

    public Root searchFlights(int adultsNum, String departure_iata_code, String destination_iata_code, String departureDate) {
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
            ResponseEntity<Root> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Root.class);

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
