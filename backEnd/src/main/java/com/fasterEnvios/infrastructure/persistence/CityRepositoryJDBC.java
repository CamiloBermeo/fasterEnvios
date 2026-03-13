package com.fasterEnvios.infrastructure.persistence;

import com.fasterEnvios.application.exceptions.jdbc.SaveErrorDataBaseException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.CityRepository;
import com.fasterEnvios.infrastructure.mapper.CityInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityRepositoryJDBC implements CityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<CityDescription> findCityByName(String name) {

        String sql = "SELECT * FROM cities WHERE name = ?";

        return jdbcTemplate.query(sql, CityInfraMapper.cityRowMapperJDBC(), name)
                .stream()
                .map(CityInfraMapper::toDomain)
                .findFirst();
    }

    //estoy guardando en base de datos
    @Override
    @Transactional
    public Optional<CityDescription> save(CityDescription city) {
        String sql = "INSERT INTO cities (name, country, latitude,longitude ) VALUES (?, ?, ?, ?)";
        try {
            int rowAffected = jdbcTemplate.update(sql,
                    city.getName(),
                    city.getCountry(),
                    city.getLongitude(),
                    city.getLatitude());
            return rowAffected > 0 ? Optional.of(city) : Optional.empty();
        } catch (DataAccessException ex) {
            throw new SaveErrorDataBaseException(city.getName());
        }

    }
}
