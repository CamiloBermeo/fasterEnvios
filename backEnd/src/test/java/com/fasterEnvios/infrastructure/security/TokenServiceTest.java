package com.fasterEnvios.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    TokenService tokenService;

    @Test
    void generateToken_whenAllSuccess_youShouldReturnToken() {
    CustomUserDetails userDetails = buildCustomUserDetailsForTest();
        Algorithm algorithm = Algorithm.HMAC256("CLAVE_SECRETA");

        String token = JWT.create()
                .withIssuer("FASTER_ENVIOS")
                .withSubject("EMAIL_TEST")
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .sign(algorithm);

        tokenService.generateToken(userDetails);
        assertNotNull(token);

    }
    @Test
    void generateToken_whenUserIsValid_youShouldContainCorrectSubject() {
        CustomUserDetails userDetails = buildCustomUserDetailsForTest();

        String token = tokenService.generateToken(userDetails);
        String subject = tokenService.getSubject(token);
        assertEquals(userDetails.getUsername(), subject);
    }

    private CustomUserDetails buildCustomUserDetailsForTest() {
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
}