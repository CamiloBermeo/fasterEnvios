package com.fasterEnvios.infrastructure.security;

import com.fasterEnvios.domain.exceptions.user.UserNotFoundException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityUserDetailServiceTest {
    @Mock
    IUserRepository userRepository;
    @InjectMocks
    SecurityUserDetailService securityUserDetailService;

    @Test
    void loadUserByUsername_whenUserNotExists_thenThrowException() {
        UserModel user = buildUserForTest();
        when(userRepository.findByEmail("EMAIL_TEST"))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->
                securityUserDetailService.loadUserByUsername("EMAIL_TEST"));
    }
    @Test
    void loadUserByUsername_whenUserExists_youShouldReturnUserDetails() {
        UserModel user = buildUserForTest();
        when(userRepository.findByEmail("EMAIL_TEST"))
                .thenReturn(Optional.of(user));
        UserDetails result = securityUserDetailService.loadUserByUsername("EMAIL_TEST");
        assertNotNull(result);
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
}