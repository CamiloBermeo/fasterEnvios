package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.Role;

import java.util.Optional;

public interface IRoleRepository {
    Optional<Role> findByName(String name);
}
