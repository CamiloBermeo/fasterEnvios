package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.domain.exceptions.user.ExistingUserDatabaseException;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.application.useCase.city.SaveCityUseCase;
import com.fasterEnvios.application.useCase.role.FindByNameRole;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.security.CustomUserDetails;
import com.fasterEnvios.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewUserUseCase {
    private final SaveCityUseCase saveCityUseCase;
    private final IUserRepository userRepository;
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final FindUserByDocument findUserByDocument;
    private final FindByNameRole findByNameRole;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public RegisterSuccessDTO execute(NewUserRequestDTO dto) {

        //si el usuario existe lanza la excepcion y si no, no hace nada y la ejecucion continua
        findUserByDocument.execute(dto.identityDocument())
                .ifPresent(userModelSave -> {
                    throw new ExistingUserDatabaseException(dto.identityDocument());
                });

        String passwordHash = passwordEncoder.encode(dto.password());
        String roleName = (dto.role() == null || dto.role().isEmpty()) ? "CLIENT" : dto.role();
        Role roleDb = findByNameRole.execute(roleName);

        CityDescription cityDb = findCityByNameUseCase.execute(dto.city())
                .orElseGet(() -> {
                    return saveCityUseCase.execute(dto.city());
                });


        UserModel user = UserAppMapper.toModel(dto, cityDb, passwordHash, roleDb);

        UserModel saveUser = userRepository.save(user);
        NewUserResponseDTO userResponseDTO = UserAppMapper.toUserResponse(saveUser);
        String token = tokenService.generateToken(new CustomUserDetails(saveUser));
        return UserAppMapper.toDto(userResponseDTO, token);
    }
}
