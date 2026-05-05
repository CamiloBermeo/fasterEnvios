package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.auth.AuthDataDTO;
import com.fasterEnvios.application.dto.auth.TokenDataDTO;
import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.NewUserResponseDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.dto.user.UserResponseDTO;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.application.useCase.user.NewUserUseCase;
import com.fasterEnvios.infrastructure.docs.StatusCode;
import com.fasterEnvios.infrastructure.security.CustomUserDetails;
import com.fasterEnvios.infrastructure.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final NewUserUseCase newUserUseCase;


    @PostMapping("login")
    @Operation(
            summary = "Devuelve el usuario que ha iniciado sesion",
            description = """
                    Este End-point es el encargado de gestionar el inicio de sesion de los usuarios registrados previamente
                    y despues retornar el token junto con los datos del usuario que ha iniciado sesion para uso del cliente
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = StatusCode.OK,
                            description = StatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TokenDataDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<TokenDataDTO> login(@RequestBody AuthDataDTO dto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = tokenService.generateToken(customUserDetails);

        return ResponseEntity.ok(new TokenDataDTO(UserAppMapper.toUserResponse(customUserDetails.getUser()), accessToken));
    }

    @PostMapping("register")
    @Operation(
            summary = "Registra un usuario nuevo y retorna token para inicio de sesion",
            description = """
                    Este end-point es el encargado de registrar un usuario nuevo despues de recibir sus datos, retorna informacion del usuario
                    junto con el token para inicio de sesion automatico sin necesidad de volver a iniciar sesion.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = StatusCode.OK,
                            description = StatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RegisterSuccessDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<RegisterSuccessDTO> register(@RequestBody NewUserRequestDTO dto) {
        RegisterSuccessDTO response = newUserUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("profile")
    @Operation(
            summary = "Obtiene el perfil del usuario autenticado",
            description = """
                    Este endpoint retorna la información del usuario actualmente autenticado en el sistema.
                    Utiliza el contexto de seguridad para identificar al usuario a partir del token de autenticación
                    y devuelve sus datos sin requerir parámetros adicionales.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = StatusCode.OK,
                            description = StatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<UserResponseDTO> myProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(UserAppMapper.toDtoProfile(customUserDetails.getUser()));
    }

}