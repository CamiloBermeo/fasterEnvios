package com.fasterEnvios.infrastructure.persistence.city;

import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepositoryJpa extends JpaRepository<CityDescriptionEntity, Long> {
    Optional<CityDescriptionEntity> findByName(String name);
}
