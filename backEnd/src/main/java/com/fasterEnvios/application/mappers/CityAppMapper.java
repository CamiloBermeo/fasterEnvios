package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.domain.model.CityDescription;

public class CityAppMapper {
    public static CityDescription toDomain (String city,String country, CityCoordinatesResponseDTO coordinates){
        return CityDescription.builder()
                .withName(city)
                .withCountry(country)
                .withLatitude(coordinates.latitude())
                .withLongitude(coordinates.longitude())
                .build();

    }

    public static CityCoordinatesRequestDTO toClientCityCoordinates(String name, String country) {
        return new  CityCoordinatesRequestDTO(
                name,
                country
        );
    }
}
