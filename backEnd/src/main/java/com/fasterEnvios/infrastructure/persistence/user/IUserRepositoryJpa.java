package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepositoryJpa extends JpaRepository<UserEntity, Long> {
}
