package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CityInfraMapper {

    public static CityDescription toDomain(CityDescriptionEntity entity) {
        CityDescription.CityDescriptionBuilder cityDescriptionBuilder = CityDescription.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withCountry(entity.getCountry())
                .withLatitude(entity.getLatitude())
                .withLongitude(entity.getLongitude())
                .build().toBuilder();
        return cityDescriptionBuilder.build();
    }

    public static CityDescriptionEntity toEntity(CityDescription cityDescription) {
        return CityDescriptionEntity.builder()
                .id(cityDescription.getId())
                .name(cityDescription.getName())
                .country(cityDescription.getCountry())
                .latitude(cityDescription.getLatitude())
                .longitude(cityDescription.getLongitude())
                .build();
    }
}
