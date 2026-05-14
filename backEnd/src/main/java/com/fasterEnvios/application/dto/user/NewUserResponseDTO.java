package com.fasterEnvios.application.dto.user;


public record NewUserResponseDTO(
        Long id,
        String name,
        String email,
        String rol
) {
}
