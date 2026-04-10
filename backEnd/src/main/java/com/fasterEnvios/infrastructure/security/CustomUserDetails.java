package com.fasterEnvios.infrastructure.security;

import com.fasterEnvios.domain.model.UserModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private final UserModel user;

    private Collection<? extends GrantedAuthority> mapAuthorities(UserModel user) {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

            roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()));

        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.mapAuthorities(user);
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;

    }
}
