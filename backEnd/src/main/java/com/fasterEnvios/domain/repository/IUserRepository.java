package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.UserModel;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserModel> findByEmail(String email);
    UserModel save(UserModel user);
}
