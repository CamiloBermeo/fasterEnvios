package com.fasterEnvios.infrastructure.security;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) {
        try {
            UserModel userModel = userRepository.findByEmail(email);
            return new CustomUserDetails(userModel);
        } catch (IllegalAccessError e) {
            throw new UsernameNotFoundException("usuario no encontrado: "+email);
        }
    }
}
