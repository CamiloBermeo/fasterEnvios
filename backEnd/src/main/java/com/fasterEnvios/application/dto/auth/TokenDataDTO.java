package com.fasterEnvios.application.dto.auth;

import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;

public record TokenDataDTO (
        NewUserResponseDTO user,
        String token){
}
