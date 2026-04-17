package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.dto.user.UserResponseDTO;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.infrastructure.entity.RoleEntity;
import org.aspectj.weaver.patterns.IToken;

public class UserAppMapper {
    public static UserModel toModel (NewUserRequestDTO dto, String passwordHash, Role role) {
        return UserModel.builder()
                .withName(dto.name())
                .withLastName(dto.lastName())
                .withEmail(dto.email())
                .withPasswordHash(passwordHash)
                .withPhoneNumber(dto.phoneNumber())
                .withIdentityDocument(dto.identityDocument())
                .withRole(role)
                .build();

    }
    public static RegisterSuccessDTO toDto(NewUserResponseDTO userResponseDTO,String token){
        return new RegisterSuccessDTO(
                userResponseDTO,
                token
        );
    }

    public static NewUserResponseDTO toUserResponse (UserModel user) {
        return new NewUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static UserResponseDTO toDtoProfile(UserModel model){
        return new UserResponseDTO(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getRole().getRoleName()
        );
    }
}
