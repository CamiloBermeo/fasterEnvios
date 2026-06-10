package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.application.useCase.city.SaveCityUseCase;
import com.fasterEnvios.application.useCase.role.FindByNameRole;
import com.fasterEnvios.domain.exceptions.user.ExistingUserDatabaseException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import com.fasterEnvios.infrastructure.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class NewUserUseCaseTest {

    @Mock
    FindUserByDocument findUserByDocument;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    TokenService tokenService;
    @Mock
    SaveCityUseCase saveCityUseCase;
    @Mock
    FindByNameRole findByNameRole;
    @Mock
    FindCityByNameUseCase findCityByNameUseCase;
    @Mock
    IUserRepository userRepository;
    @InjectMocks
    NewUserUseCase newUserUseCase;

    @Test
    void execute_whenUserIsPresent_youShouldThrowException() {
        NewUserRequestDTO dto = buildNewUserRequestDTOfOrTest();
        UserModel user = buildUserForTest();

        when(findUserByDocument.execute("1234"))
                .thenReturn(Optional.of(user));

        assertThrows(ExistingUserDatabaseException.class, ()-> newUserUseCase.execute(dto));
    }

    @Test
    void execute_whenRegisterIsSuccess_youShouldReturnRegisterSuccessDto(){
        NewUserRequestDTO dto = buildNewUserRequestDTOfOrTest();
        CityDescription city = buildCityForTest();
        UserModel user = buildUserForTest();
        Role role = buildRoleForTest();
        when(findUserByDocument.execute("1234")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("PASSWORD_TEST")).thenReturn("PASSWORD_TEST");
        when(findByNameRole.execute("ROLE_TEST")).thenReturn(role);
        when(tokenService.generateToken(any())).thenReturn("TOKEN");
        when(findCityByNameUseCase.execute("CITY_TEST")).thenReturn(Optional.of(city));
        when(userRepository.save(any(UserModel.class))).thenReturn(user);
        RegisterSuccessDTO response = newUserUseCase.execute(dto);

        assertNotNull(response);
    }
    @Test
    void execute_whenRegisterIsSuccessWithCallSaveCity_youShouldReturnRegisterSuccessDto(){
        NewUserRequestDTO dto = buildNewUserRequestDTOfOrTest();
        CityDescription city = buildCityForTest();
        UserModel user = buildUserForTest();
        Role role = buildRoleForTest();
        when(findUserByDocument.execute("1234")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("PASSWORD_TEST")).thenReturn("PASSWORD_TEST");
        when(findByNameRole.execute("ROLE_TEST")).thenReturn(role);
        when(tokenService.generateToken(any())).thenReturn("TOKEN");
        when(findCityByNameUseCase.execute("CITY_TEST")).thenReturn(Optional.empty());
        when(saveCityUseCase.execute(city.getName())).thenReturn(city);
        when(userRepository.save(any(UserModel.class))).thenReturn(user);
        RegisterSuccessDTO response = newUserUseCase.execute(dto);

        assertNotNull(response);
        verify(saveCityUseCase, times(1)).execute(any());
    }

    @Test
    void execute_whenRoleIsEmpty_youShouldAssignedRoleClient(){
        NewUserRequestDTO dto = new NewUserRequestDTO(
                "NOMBRE_TEST",
                "APELLIDO_TEST",
                "EMAIL_TEST",
                "PASSWORD_TEST",
                "CITY_TEST",
                "CELULAR_TEST",
                "1234",
                ""
        );
        CityDescription city = buildCityForTest();
        UserModel user = buildUserForTest();
        Role role = buildRoleForTest();

        when(findUserByDocument.execute("1234")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("PASSWORD_TEST")).thenReturn("PASSWORD_TEST");
        when(findByNameRole.execute("CLIENT")).thenReturn(role);
        when(tokenService.generateToken(any())).thenReturn("TOKEN");
        when(findCityByNameUseCase.execute("CITY_TEST")).thenReturn(Optional.of(city));
        when(userRepository.save(any(UserModel.class))).thenReturn(user);
        newUserUseCase.execute(dto);
        verify(findByNameRole, times(1)).execute("CLIENT");
    }
    @Test
    void execute_whenRoleIsNull_youShouldAssignedRoleClient(){
        NewUserRequestDTO dto = new NewUserRequestDTO(
                "NOMBRE_TEST",
                "APELLIDO_TEST",
                "EMAIL_TEST",
                "PASSWORD_TEST",
                "CITY_TEST",
                "CELULAR_TEST",
                "1234",
                null
        );
        CityDescription city = buildCityForTest();
        UserModel user = buildUserForTest();
        Role role = buildRoleForTest();

        when(findUserByDocument.execute("1234")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("PASSWORD_TEST")).thenReturn("PASSWORD_TEST");
        when(findByNameRole.execute("CLIENT")).thenReturn(role);
        when(tokenService.generateToken(any())).thenReturn("TOKEN");
        when(findCityByNameUseCase.execute("CITY_TEST")).thenReturn(Optional.of(city));
        when(userRepository.save(any(UserModel.class))).thenReturn(user);
        newUserUseCase.execute(dto);
        verify(findByNameRole, times(1)).execute("CLIENT");
    }
    private CityDescription buildCityForTest(){
        return CityDescription.builder()
                .withId(4L)
                .withName("CITY_TEST")
                .withCountry("CALI")
                .withLongitude(123.4)
                .withLatitude(432.1)
                .build();
    }
    private Role buildRoleForTest(){
        return Role.builder()
                .withId(6L)
                .withRoleName("ROLE_TEST")
                .withDescription("DESCRIPTION_ROLE_TEST")
                .build();
    }
    private UserModel buildUserForTest() {
        CityDescription city = CityDescription.builder()
                .withId(3L)
                .withName("Cali")
                .withCountry("Colombia")
                .withLatitude(1232.4)
                .withLongitude(432.1)
                .build();
        Role role = Role.builder()
                .withId(5L)
                .withRoleName("ROLE_TEST")
                .withDescription("DESCRIPTION_TEST")
                .build();
        return UserModel.builder()
                .withId(2L)
                .withName("NOMBRE_TEST")
                .withEmail("EMAIL_TEST")
                .withIdentityDocument("1234")
                .withCity(city)
                .withPasswordHash("PASSWORD_TEST")
                .withRole(role)
                .build();
    }

    private NewUserRequestDTO buildNewUserRequestDTOfOrTest() {
        return new NewUserRequestDTO(
                "NOMBRE_TEST",
                "APELLIDO_TEST",
                "EMAIL_TEST",
                "PASSWORD_TEST",
                "CITY_TEST",
                "CELULAR_TEST",
                "1234",
                "ROLE_TEST"
        );

    }
}