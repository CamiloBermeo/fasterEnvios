package com.fasterEnvios.application.dto.user;

public record RegisterSuccessDTO(
        NewUserResponseDTO userResponse,
        String Token
) {
}
