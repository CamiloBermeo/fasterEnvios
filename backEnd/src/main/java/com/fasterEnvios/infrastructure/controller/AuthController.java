package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.auth.AuthDataDTO;
import com.fasterEnvios.application.dto.auth.TokenDataDTO;
import com.fasterEnvios.application.dto.user.NewUserRequestDTO;
import com.fasterEnvios.application.dto.user.RegisterSuccessDTO;
import com.fasterEnvios.application.dto.user.UserResponseDTO;
import com.fasterEnvios.application.mappers.UserAppMapper;
import com.fasterEnvios.application.useCase.user.NewUserUseCase;
import com.fasterEnvios.infrastructure.security.CustomUserDetails;
import com.fasterEnvios.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<TokenDataDTO> login (@RequestBody AuthDataDTO dto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = tokenService.generateToken(customUserDetails);

        return ResponseEntity.ok(new TokenDataDTO(accessToken));
    }

    @PostMapping("register")
    public ResponseEntity<RegisterSuccessDTO> register(@RequestBody NewUserRequestDTO dto){
        RegisterSuccessDTO response= newUserUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("profile")
    public ResponseEntity<UserResponseDTO> myProfile (@AuthenticationPrincipal CustomUserDetails customUserDetails){
    return ResponseEntity.ok(UserAppMapper.toDtoProfile(customUserDetails.getUser()));
    }

}