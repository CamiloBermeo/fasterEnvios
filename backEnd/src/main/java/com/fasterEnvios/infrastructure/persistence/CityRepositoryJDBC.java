package com.fasterEnvios.infrastructure.persistence;

import com.fasterEnvios.application.exceptions.jdbc.SaveErrorDataBaseException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.CityRepository;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.mapper.CityInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityRepositoryJDBC implements CityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<CityDescription> findCityByName(String name) {

        String sql = "SELECT * FROM city WHERE name = ?";

        return jdbcTemplate.query(sql, CityInfraMapper.cityRowMapperJDBC(), name)
                .stream()
                .map(CityInfraMapper::toDomain)
                .findFirst();
    }

    //estoy guardando en base de datos
    @Override
    public Optional<CityDescription> save(CityDescription city) {
        String sql = "INSERT INTO cities (name, origin, longitude, latitude) VALUES (?, ?, ?, ?)";
        int rowAffected = jdbcTemplate.update(sql,
                city.getName(),
                city.getCountry(),
                city.getLongitude(),
                city.getLatitude());
        if (rowAffected == 0) {
            throw new SaveErrorDataBaseException(city.getName());
        }
        return findCityByName(city.getName());
    }
}
