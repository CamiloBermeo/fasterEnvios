package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.CityDescription;

import java.util.Optional;

public interface CityRepository {

    Optional<CityDescription> findCityByName(String name);
    Optional<CityDescription> save(CityDescription city);

}
