package com.backend.repository;

import com.backend.domain.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    @Override
    List<Trip> findAll();

    @Override
    Optional<Trip> findById(Long id);

    @Override
    Trip save(Trip trip);
}
