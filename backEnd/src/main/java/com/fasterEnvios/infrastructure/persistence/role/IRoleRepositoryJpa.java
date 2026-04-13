package com.fasterEnvios.infrastructure.persistence.role;

import com.fasterEnvios.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepositoryJpa extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(String name);
}
