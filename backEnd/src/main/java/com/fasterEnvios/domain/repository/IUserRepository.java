package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.UserModel;

public interface IUserRepository {
    UserModel findByEmail(String email);
    UserModel save(UserModel user);
}
