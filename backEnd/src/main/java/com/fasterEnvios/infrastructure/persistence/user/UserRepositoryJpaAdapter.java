package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.domain.exceptions.city.CityNotFoundException;
import com.fasterEnvios.domain.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.ICityRepository;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.RoleEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;
import com.fasterEnvios.infrastructure.mapper.UserInfraMapper;
import com.fasterEnvios.infrastructure.persistence.city.ICityRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.role.IRoleRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements IUserRepository {
    private final IUserRepositoryJpa jpa;
    private final ICityRepositoryJpa cityJpa;
    private final IRoleRepositoryJpa roleJpa;


    @Override
    public Optional<UserModel> findByEmail(String email) {
        Optional<UserEntity> entity = jpa.findByEmail(email);
        return entity.map(UserInfraMapper::toModel);
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = buildManagedEntity(user);
        return UserInfraMapper.toModel(jpa.save(entity));
    }

    @Override
    public Optional<UserModel> findByDocument(String document) {
        Optional<UserEntity> entity = jpa.findByIdentityDocument(document);
        return entity.map(UserInfraMapper::toModel);
    }

    private UserEntity buildManagedEntity(UserModel userModel) {
        CityDescriptionEntity city = cityJpa.findById(userModel.getCity().getId())
                .orElseThrow(() -> new CityNotFoundException(userModel.getCity().getName()));
        RoleEntity role = roleJpa.findById(userModel.getRole().getId())
                .orElseThrow(() -> new RoleNotFoundDataBaseException(userModel.getRole().getRoleName()));
        UserEntity entity = UserInfraMapper.toEntity(userModel);
        entity.setCity(city);
        entity.setRole(role);
        return entity;
    }
}