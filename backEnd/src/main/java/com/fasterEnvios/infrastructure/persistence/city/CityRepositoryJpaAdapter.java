package com.fasterEnvios.infrastructure.persistence.city;

import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.ICityRepository;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.mapper.CityInfraMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityRepositoryJpaAdapter implements ICityRepository {

    private final ICityRepositoryJpa jpa;

    @Override
    @Transactional
    public Optional<CityDescription> findCityByName(String name) {
        Optional<CityDescriptionEntity> result = jpa.findByName(name);
        return result.map(CityInfraMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<CityDescription> save(CityDescription city) {

        CityDescriptionEntity cityEntity = CityInfraMapper.toEntity(city);
        Optional<CityDescriptionEntity> result = jpa.save(cityEntity);
        return result.map(CityInfraMapper::toDomain);
    }
}
