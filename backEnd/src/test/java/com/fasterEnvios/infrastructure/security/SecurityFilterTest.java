package com.fasterEnvios.infrastructure.security;

import com.fasterEnvios.domain.exceptions.user.UserNotFoundException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {
    @Mock
    TokenService tokenService;
    @Mock
    IUserRepository userRepository;
    @InjectMocks
    SecurityFilter securityFilter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }
    @Test
    void doFilterInternal_whenUserNotExist_youShouldThrowUserNotFoundException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        String tokenJwt = "TOKEN";

        request.addHeader("Authorization", "Bearer TOKEN");

        when(tokenService.getSubject(tokenJwt))
                .thenReturn("EMAIL_TEST");

        when(userRepository.findByEmail("EMAIL_TEST"))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                securityFilter.doFilterInternal(request, response, chain));
    }

    @Test
    void doFilterInternal_whenIsSuccess_youShouldApplyMyInternalFilter() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        UserModel user = buildUserForTest();
        String tokenJwt = "TOKEN";
        request.addHeader("Authorization", "Bearer TOKEN");

        when(tokenService.getSubject(tokenJwt))
                .thenReturn("EMAIL_TEST");

        when(userRepository.findByEmail("EMAIL_TEST"))
                .thenReturn(Optional.of(user));

        securityFilter.doFilterInternal(request, response, chain);
        Authentication result = SecurityContextHolder.getContext().getAuthentication();

        assertNotNull(result);
        assertTrue(result.isAuthenticated());

    }
    @Test
    void doFilterInternal_whenTokenIsNull_shouldNotSetAuthentication() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        securityFilter.doFilterInternal(request, response, chain);

        Authentication result = SecurityContextHolder.getContext().getAuthentication();
        assertNull(result);
    }
    @Test
    void doFilterInternal_whenRecoverTokenReturnNull_youShouldNotSetAuthentication() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        String tokenJwt = "TOKEN";
        request.addHeader("TEST_AUTHORIZATION", "Bearer TOKEN");
        securityFilter.doFilterInternal(request, response, chain);
        Authentication result = SecurityContextHolder.getContext().getAuthentication();

        assertNull(result);
    }
    @Test
    void doFilterInternal_whenRecoverTokenReturnNullWithoutBearer_youShouldNotSetAuthentication() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        String tokenJwt = "TOKEN";
        request.addHeader("Authorization", "BEARER_TEST");
        securityFilter.doFilterInternal(request, response, chain);
        Authentication result = SecurityContextHolder.getContext().getAuthentication();

        assertNull(result);

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