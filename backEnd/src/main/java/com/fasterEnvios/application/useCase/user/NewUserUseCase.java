package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.application.exceptions.user.ExistingUserDatabaseException;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.application.useCase.role.FindByNameRole;
import com.fasterEnvios.domain.model.CityDescription;
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
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final FindUserByDocument findUserByDocument;
    private final FindByNameRole findByNameRole;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    /*
    * 3. verificar y asignar la ciudad
    * */
    public RegisterSuccessDTO execute(NewUserRequestDTO dto){
        Role roleDb;
        UserModel user;
        //si el usuario existe lanza la excepcion y si no, no hace nada y la ejecucion continua
        findUserByDocument.execute(dto.identityDocument())
                .ifPresent(userModelSave -> {
                    throw new ExistingUserDatabaseException(dto.identityDocument());
                });

        String passwordHash = passwordEncoder.encode(dto.password());

        roleDb = (dto.role()==null || dto.role().isEmpty()) ?
                findByNameRole.execute("CLIENTE") :
                findByNameRole.execute(dto.role());

        CityDescription cityDb = findCityByNameUseCase.execute(dto.city());

        user = UserAppMapper.toModel(dto,passwordHash, roleDb);



        UserModel saveUser = userRepository.save(user);
        NewUserResponseDTO userResponseDTO = UserAppMapper.toUserResponse(saveUser);
        String token = tokenService.generateToken(new CustomUserDetails(saveUser));
        return UserAppMapper.toDto(userResponseDTO, token);
    }

}
