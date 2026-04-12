package com.fasterEnvios.infrastructure.security;

import com.fasterEnvios.application.exceptions.user.UserNotFoundException;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;


    @NullMarked
    public UserDetails loadUserByUsername(String email) {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return new CustomUserDetails(userModel);
    }
}
