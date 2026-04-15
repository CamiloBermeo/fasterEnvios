package com.fasterEnvios.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(CustomUserDetails customUserDetails) {
        try {
            //se encripta la clave secreta con encriptacion HMAC256
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("fasterEnvios")//se firma el token para saber que pertenece a este proyecto
                    .withSubject(customUserDetails.getUsername())//se extrae el username que es el email en userdetails
                    .withExpiresAt(expirationDate())//se le da un tiempo de expiracion
                    .sign(algorithm);//y se le aigna la clave secreta codificada
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error creating token");
        }
    }

    private Instant  expirationDate() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
    protected String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.require(algorithm)
                    .withIssuer("fasterEnvios")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e) {
            throw new RuntimeException("Error verifying token");
        }
    }

}
