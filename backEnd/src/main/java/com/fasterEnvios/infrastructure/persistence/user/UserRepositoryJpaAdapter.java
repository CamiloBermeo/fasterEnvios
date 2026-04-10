package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.entity.UserEntity;
import com.fasterEnvios.infrastructure.mapper.UserInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements IUserRepository {
private final IUserRepositoryJpa jpa;


    @Override
    public UserModel findByEmail(String email) {
        UserEntity entity = jpa.findByEmail(email);
        return UserInfraMapper.toModel(entity);
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = UserInfraMapper.toEntity(user);
        return UserInfraMapper.toModel(jpa.save(entity));
    }
}
