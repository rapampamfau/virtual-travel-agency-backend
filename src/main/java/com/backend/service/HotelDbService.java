package com.backend.service;

import com.backend.domain.Hotel;
import com.backend.exception.HotelNotFoundException;
import com.backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelDbService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(final Long hotelId) throws HotelNotFoundException {
        return hotelRepository.findById(hotelId).orElseThrow(HotelNotFoundException::new);
    }

    public Hotel saveHotel(final Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(final Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
