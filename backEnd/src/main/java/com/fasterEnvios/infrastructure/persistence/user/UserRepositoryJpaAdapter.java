package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements IUserRepository {
private final IUserRepositoryJpa jpa;


    @Override
    public UserModel findByEmail(String email) {
        return null;
    }

    @Override
    public UserModel save(UserModel user) {
        return null;
    }
}
