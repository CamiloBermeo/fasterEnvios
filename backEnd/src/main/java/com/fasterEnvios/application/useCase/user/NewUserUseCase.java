package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.application.useCase.role.FindByNameRole;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IRoleRepository;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.security.CustomUserDetails;
import com.fasterEnvios.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewUserUseCase {

    private final IUserRepository userRepository;
    private final FindByNameRole findByNameRole;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public RegisterSuccessDTO execute(NewUserRequestDTO dto){
        String passwordHash = passwordEncoder.encode(dto.password());
        //tengo que buscar el rol en la db
        Role roleDb = findByNameRole.execute(dto.role());
        UserModel user = UserAppMapper.toModel(dto,passwordHash, roleDb);

        UserModel saveUser = userRepository.save(user);
        NewUserResponseDTO userResponseDTO = UserAppMapper.toUserResponse(saveUser);
        String token = tokenService.generateToken(new CustomUserDetails(saveUser));
        return UserAppMapper.toDto(userResponseDTO, token);
    }

}
