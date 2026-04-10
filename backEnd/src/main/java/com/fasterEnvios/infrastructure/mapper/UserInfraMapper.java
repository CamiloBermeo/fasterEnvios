package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.infrastructure.entity.UserEntity;

public class UserInfraMapper {

    public static UserEntity toEntity (UserModel user) {
        return UserEntity.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .identityDocument(user.getIdentityDocument())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .passwordHash(user.getPasswordHash())
                .role(RoleInfraMapper.toEntity(user.getRole()))
                .build();
    }

    public static UserModel toModel(UserEntity entity) {
        UserModel.UserModelBuilder builder = UserModel.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withLastName(entity.getLastName())
                .withEmail(entity.getEmail())
                .withIdentityDocument(entity.getIdentityDocument())
                .withPhoneNumber(entity.getPhoneNumber())
                .withRole(RoleInfraMapper.toModel(entity.getRole()));
        return builder.build();
    }

}
