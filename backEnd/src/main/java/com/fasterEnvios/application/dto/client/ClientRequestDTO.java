package com.fasterEnvios.application.dto.client;

import java.util.List;

public record ClientRequestDTO(
        List<double[]> coordinates,
        String units,
        String language
) {
}
