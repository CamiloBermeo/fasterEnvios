package com.fasterEnvios.infrastructure.persistence.role;

import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.repository.IRoleRepository;
import com.fasterEnvios.infrastructure.entity.RoleEntity;
import com.fasterEnvios.infrastructure.mapper.RoleInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryJpaAdapter implements IRoleRepository {
    private final IRoleRepositoryJpa jpa;

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = jpa.findByName(name);
        return roleEntity.map(RoleInfraMapper :: toModel);
    }
}
