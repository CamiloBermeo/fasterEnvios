package com.fasterEnvios.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewUserRequestDTO(
        @NotBlank(message = "El campo Nombre es obligatorio")
        String name,
        @NotBlank(message = "El campo Apellidos es obligatorio")
        String lastName,
        @Email
        String email,
        @NotBlank(message = "El campo Contraseña es obligatorio")
        String password,
        @NotBlank(message = "El campo Ciudad es obligatorio")
        String city,
        @NotBlank(message = "El campo Numero de Telefono es obligatorio")
        String phoneNumber,
        @NotBlank(message = "El campo Documento de Identidad es obligatorio")
        String identityDocument,
        @NotBlank(message = "El campo rol es obligatorio")
        String role
) {
}
