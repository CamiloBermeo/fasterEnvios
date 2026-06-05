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
    @Column(nullable = false,  unique = true)
    private String email;
    @Column(nullable = false,  unique = true)
    private String passwordHash;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityDescriptionEntity city;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
