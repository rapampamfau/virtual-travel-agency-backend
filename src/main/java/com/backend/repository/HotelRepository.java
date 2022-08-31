package com.backend.repository;

import com.backend.domain.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    @Override
    List<Hotel> findAll();

    @Override
    Optional<Hotel> findById(Long id);

    @Override
    Hotel save(Hotel hotel);
}
