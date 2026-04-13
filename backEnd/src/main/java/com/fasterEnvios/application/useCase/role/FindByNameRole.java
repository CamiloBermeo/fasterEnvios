package com.fasterEnvios.application.useCase.role;

import com.fasterEnvios.application.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByNameRole {
    private final IRoleRepository roleRepository;

    public Role execute (String role){
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RoleNotFoundDataBaseException(role));
    }
}
