package com.fasterEnvios.application.useCase.role;

import com.fasterEnvios.domain.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.repository.IRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByNameRoleTest {

    @Mock
    IRoleRepository roleRepository;

    @InjectMocks
    FindByNameRole findByNameRole;

    @Test
    void execute_whenRoleNotFound_youShouldThrowRoleNotFoundException() {

        when(roleRepository.findByName("ADMIN_FALSO"))
                .thenReturn(Optional.empty());

        assertThrows(RoleNotFoundDataBaseException.class,
                () -> findByNameRole.execute("ADMIN_FALSO"));
    }

    @Test
    void execute_whenRoleExist_youShouldReturnRole() {

        Role role = buildRoleForTest();

        when(roleRepository.findByName("ADMIN_FALSO"))
                .thenReturn(Optional.of(role));

        Role roleDb = findByNameRole.execute("ADMIN_FALSO");
        assertEquals(role, roleDb);
    }
    private Role buildRoleForTest(){
        return Role.builder()
                .withId(1L)
                .withRoleName("ADMIN_FALSO")
                .withDescription("test para un rol")
                .build();

    }
}

