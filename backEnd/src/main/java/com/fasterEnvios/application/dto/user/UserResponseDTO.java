package com.fasterEnvios.application.dto.user;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String rol
) {
}
