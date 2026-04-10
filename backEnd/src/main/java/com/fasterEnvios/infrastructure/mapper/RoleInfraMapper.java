package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.infrastructure.entity.RoleEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;

public class RoleInfraMapper {

    public static RoleEntity toEntity(Role role) {
        return RoleEntity.builder()
                .name(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    public static Role toModel (RoleEntity entity) {
        Role.RoleBuilder builder = Role.builder()
                .withId(entity.getId())
                .withRoleName(entity.getName())
                .withDescription(entity.getDescription());
        return builder.build();
    }

}
