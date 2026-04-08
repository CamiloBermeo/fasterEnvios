package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,  unique = true)
    private String email;
    @Column(nullable = false,  unique = true)
    private String passwordHash;
    @Column(nullable = false,  unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String identityDocument;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
