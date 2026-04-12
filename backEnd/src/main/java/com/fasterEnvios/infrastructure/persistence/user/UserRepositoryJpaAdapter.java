package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.entity.UserEntity;
import com.fasterEnvios.infrastructure.mapper.UserInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements IUserRepository {
private final IUserRepositoryJpa jpa;


    @Override
    public Optional<UserModel> findByEmail(String email) {
        Optional<UserEntity> entity = jpa.findByEmail(email);
        return entity.map(UserInfraMapper::toModel);
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = UserInfraMapper.toEntity(user);
        return UserInfraMapper.toModel(jpa.save(entity));
    }
}
