package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private List<RoleEntity> roles;
}
