package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.auth.AuthDataDTO;
import com.fasterEnvios.application.dto.auth.TokenDataDTO;
import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.dto.user.UserResponseDTO;
import com.fasterEnvios.application.useCase.user.NewUserUseCase;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.infrastructure.security.CustomUserDetails;
import com.fasterEnvios.infrastructure.security.TokenService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    TokenService tokenService;
    @Mock
    NewUserUseCase newUserUseCase;
    @InjectMocks
    AuthController authController;

    @Test
    void login_whenIsSuccess_youShouldGenerateDtoResponse() {
        AuthDataDTO dto = buildAuthDataDtoForTest();
        CustomUserDetails userDetails = buildCustomUserDetails();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        when(authenticationManager.authenticate(any()))
                .thenReturn(authenticationToken);
        when(tokenService.generateToken(any()))
                .thenReturn("TOKEN");

        ResponseEntity<TokenDataDTO> result = authController.login(dto);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void register_whenIsSuccess_youShouldGenerateDtoResponse() {
        NewUserRequestDTO dto = buildNewUserRequestTest();
        RegisterSuccessDTO successDto = buildRegisterSuccessDtoForTest();
        when(newUserUseCase.execute(any()))
                .thenReturn(successDto);

        ResponseEntity<RegisterSuccessDTO> result = authController.register(dto);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void myProfile_whenIsSuccess_youShouldGenerateDtoResponse() {
        CustomUserDetails userDetails = buildCustomUserDetails();

        ResponseEntity<UserResponseDTO> result = authController.myProfile(userDetails);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userDetails.getUser().getName(), result.getBody().name());
    }


    private RegisterSuccessDTO buildRegisterSuccessDtoForTest() {
        NewUserResponseDTO responseDto = new NewUserResponseDTO(
                1L,
                "NOMBRE_TEST",
                "EMAIL_TEST",
                "ROL_TEST"
        );
        return new RegisterSuccessDTO(
                responseDto,
                "TOKEN"
        );
    }

    private NewUserRequestDTO buildNewUserRequestTest() {
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

    private CustomUserDetails buildCustomUserDetails() {
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
        UserModel user = UserModel.builder()
                .withId(2L)
                .withName("NOMBRE_TEST")
                .withEmail("EMAIL_TEST")
                .withIdentityDocument("1234")
                .withCity(city)
                .withPasswordHash("PASSWORD_TEST")
                .withRole(role)
                .build();

        return new CustomUserDetails(user);
    }

    private AuthDataDTO buildAuthDataDtoForTest() {
        return new AuthDataDTO(
                "EMAIL_TEST",
                "PASSWORD_TEST"
        );
    }
}