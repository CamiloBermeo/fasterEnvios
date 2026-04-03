package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class CityDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false ,length = 100)
    String name;
    @Column(nullable = false ,length = 100)
    String country;
    @Column(nullable = false )
    double latitude;
    @Column(nullable = false)
    double longitude;
}
