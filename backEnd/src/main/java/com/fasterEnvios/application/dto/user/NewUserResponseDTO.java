package com.fasterEnvios.application.dto.user;

import java.time.LocalDateTime;

public record NewUserResponseDTO(
        Long id,
        String name,
        String email
) {
}
