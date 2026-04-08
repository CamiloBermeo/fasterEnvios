package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewUserUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public RegisterSuccessDTO execute(NewUserRequestDTO dto){
        String passwordHash = passwordEncoder.encode(dto.password());
        UserModel user = UserAppMapper.toModel(dto,passwordHash);
        UserModel saveUser = userRepository.save(user);
        NewUserResponseDTO userResponseDTO = UserAppMapper.toUserResponse(saveUser);
        tokenService.generateToken()
        return UserAppMapper.toDto(userResponseDTO, token);
    }

}
