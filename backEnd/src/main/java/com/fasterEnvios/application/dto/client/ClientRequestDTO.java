package com.fasterEnvios.application.dto.client;

import java.util.List;

public record ClientRequestDTO(
        List<String> coordinates,
        String units,
        String language
) {
}
