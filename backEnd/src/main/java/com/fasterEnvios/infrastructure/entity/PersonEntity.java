package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String identityDocument;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityDescriptionEntity city;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
