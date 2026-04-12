package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.CityDescription;

import java.util.Optional;

public interface ICityRepository {

    Optional<CityDescription> findCityByName(String name);
    CityDescription save(CityDescription city);

}
